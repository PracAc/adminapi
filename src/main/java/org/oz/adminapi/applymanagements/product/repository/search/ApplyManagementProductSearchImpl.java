package org.oz.adminapi.applymanagements.product.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.applymanagements.product.dto.ApplyManagementProductListDTO;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.product.domain.ProductEntity;
import org.oz.adminapi.product.domain.QCategoryEntity;
import org.oz.adminapi.product.domain.QProductCategoryEntity;
import org.oz.adminapi.product.domain.QProductEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class ApplyManagementProductSearchImpl extends QuerydslRepositorySupport implements ApplyManagementProductSearch {

    public ApplyManagementProductSearchImpl() {super(ProductEntity.class);}

    @Override
    public PageResponseDTO<ApplyManagementProductListDTO> applyProductList(PageRequestDTO pageRequestDTO) {

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

        JPQLQuery<ApplyManagementProductListDTO> dtojpqlQuery = query.select(
                Projections.bean(ApplyManagementProductListDTO.class,
                        product.productNo,
                        category.categoryNo,
                        product.productName,
                        product.regDate,
                        product.productStatus
                )
        );

        java.util.List<ApplyManagementProductListDTO> dtoList = dtojpqlQuery.fetch();

        dtoList.forEach(log::info);

        long total = dtojpqlQuery.fetchCount();

        return PageResponseDTO.<ApplyManagementProductListDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }
}
