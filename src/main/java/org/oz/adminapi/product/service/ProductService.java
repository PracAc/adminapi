package org.oz.adminapi.product.service;

import lombok.RequiredArgsConstructor;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.maker.dto.MakerModifyDTO;
import org.oz.adminapi.product.domain.ProductEntity;
import org.oz.adminapi.product.dto.ProductListDTO;
import org.oz.adminapi.product.dto.ProductModifyDTO;
import org.oz.adminapi.product.dto.ProductReadDTO;
import org.oz.adminapi.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public PageResponseDTO<ProductListDTO> getProductList(PageRequestDTO pageRequestDTO) {

        return productRepository.productList(pageRequestDTO);
    }

    public ProductReadDTO readProduct(Long productNo) {

        Optional<Object[]> result = productRepository.findWithFilesByProductNo(productNo);

        if (result.isEmpty()) {
            throw new RuntimeException("ㄴㄴㄴㄴㄴㄴㄴ");
        }



//        ProductReadDTO readDTO = ProductReadDTO.builder()
//                .productNo(productNo)
//                .productName(result.get(0)[1])
//                .build();



//        List<String> attachFileNames = entity.getAttachFiles().stream()
//                .map(file -> file.getFileName())
//                .toList();
//
//        ProductReadDTO productReadDTO = ProductReadDTO.builder()
//                .productNo(productNo)
//                .productName(entity.getProductName())
//                .productDescription(entity.getProductDescription())
//                .productStatus(entity.getProductStatus())
//                .attachFileNames(attachFileNames)
//                .makerName(entity.getMaker().getMakerName())
//                .createDate(entity.getCreateDate())
//                .lastModifiedDate(entity.getLastModifiedDate())
//                .build();

//        return productReadDTO;
        return null;
    }

    public Long modifyProduct (ProductModifyDTO modifyDTO) {

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(modifyDTO.getProductNo());
        if (optionalProductEntity.isEmpty()) {
            throw new RuntimeException("ㄴㄴㄴㄴㄴㄴㄴ");
        }

        ProductEntity updateProductEntity = optionalProductEntity.get();
        updateProductEntity.changeStatus(modifyDTO.getProductStatus());


        return updateProductEntity.getProductNo();
    }
}
