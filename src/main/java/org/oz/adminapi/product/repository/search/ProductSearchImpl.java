package org.oz.adminapi.product.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.product.domain.ProductEntity;
import org.oz.adminapi.product.domain.QCategoryEntity;
import org.oz.adminapi.product.domain.QProductCategoryEntity;
import org.oz.adminapi.product.domain.QProductEntity;
import org.oz.adminapi.product.dto.ProductListDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {super(ProductEntity.class);}

    @Override
    public PageResponseDTO<ProductListDTO> productList(PageRequestDTO pageRequestDTO) {

        log.info("---------- product list ----------");

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("productNo").descending());

        QProductEntity product = QProductEntity.productEntity;
        QCategoryEntity category = QCategoryEntity.categoryEntity;
        QProductCategoryEntity productCategory = QProductCategoryEntity.productCategoryEntity;

        JPQLQuery<ProductEntity> query = from(product);
        query.leftJoin(productCategory).on(productCategory.product.eq(product));
        query.leftJoin(category).on(productCategory.category.eq(category));

        query.groupBy(product);

        this.getQuerydsl().applyPagination(pageable,query);

        JPQLQuery<ProductListDTO> dtojpqlQuery = query.select(
                Projections.bean(ProductListDTO.class,
                        product.productNo,
                        category.categoryNo,
                        product.productName,
                        product.regDate,
                        product.productStatus
                ));
        // .where(product.productStatus.eq(BasicStatus.ACCEPTED));

        java.util.List<ProductListDTO> dtoList = dtojpqlQuery.fetch();

        dtoList.forEach(log::info);

        long total = dtojpqlQuery.fetchCount();

        return PageResponseDTO.<ProductListDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }
}

