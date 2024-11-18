package org.oz.adminapi.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.domain.AttachFile;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.maker.dto.MakerListDTO;
import org.oz.adminapi.maker.dto.MakerModifyDTO;
import org.oz.adminapi.maker.dto.MakerSearchDTO;
import org.oz.adminapi.product.domain.CategoryEntity;
import org.oz.adminapi.product.domain.ProductEntity;
import org.oz.adminapi.product.dto.ProductListDTO;
import org.oz.adminapi.product.dto.ProductModifyDTO;
import org.oz.adminapi.product.dto.ProductReadDTO;
import org.oz.adminapi.product.dto.ProductSearchDTO;
import org.oz.adminapi.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public PageResponseDTO<ProductListDTO> getProductList(PageRequestDTO pageRequestDTO) {

        return productRepository.productList(pageRequestDTO);
    }

    // 상품 검색
    public PageResponseDTO<ProductListDTO> searchProductList(PageRequestDTO pageRequestDTO, ProductSearchDTO productSearchDTO) {
        return productRepository.searchProduct(pageRequestDTO, productSearchDTO);
    }

    public ProductReadDTO getProductWithDetails(Long productNo) {
        List<Object[]> result = productRepository.findWithFilesByProductNo(productNo);

        if (result.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        // ProductEntity를 조회
        ProductEntity product = (ProductEntity) result.get(0)[0];

        // CategoryEntity를 조회
        List<CategoryEntity> categoryEntityList = result.stream().map(arr -> (CategoryEntity) arr[1]).collect(Collectors.toList());

        // 카테고리 번호 목록 생성
        List<Long> categoriesNo = categoryEntityList.stream().map(arr -> arr.getCategoryNo()).collect(Collectors.toList());

        // 카테고리 이름 목록 생성
        List<String> categoriesName = categoryEntityList.stream().map(arr -> arr.getCategoryName()).collect(Collectors.toList());


//        // 파일 이름 목록 생성
         List<String> attachFileNames = product.getAttachFiles().stream()
                .map(AttachFile::getFileName)
                 .collect(Collectors.toList());

        // ProductReadDTO 생성
        ProductReadDTO readDTO = ProductReadDTO.builder()
                .productNo(product.getProductNo())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
//                .productStatus(product.getProductStatus())
                .makerName(product.getMaker().getMakerName())
                .categoriesNo(categoriesNo)
                .categoriesName(categoriesName)
                .attachFileNames(attachFileNames)
                .createDate(product.getRegDate())
                .lastModifiedDate(product.getModDate())
                .creatorName(product.getCreator())
                .build();
        return readDTO;
    }

    public Long modifyProduct (ProductModifyDTO modifyDTO) {

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(modifyDTO.getProductNo());
        if (optionalProductEntity.isEmpty()) {
            throw new RuntimeException("ㄴㄴㄴㄴㄴㄴㄴ");
        }

        ProductEntity updateProductEntity = optionalProductEntity.get();
//        updateProductEntity(modifyDTO.getProductStatus());


        return updateProductEntity.getProductNo();
    }
}
