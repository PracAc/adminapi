package org.oz.adminapi.applymanagements.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.applymanagements.product.dto.ApplyManagementProductListDTO;
import org.oz.adminapi.applymanagements.product.dto.ApplyManagementProductModifyDTO;
import org.oz.adminapi.applymanagements.product.dto.ApplyManagementProductReadDTO;
import org.oz.adminapi.applymanagements.product.repository.ApplyManagementProductRepository;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.product.domain.CategoryEntity;
import org.oz.adminapi.product.domain.ProductEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ApplyManagementProductService {

    private final ApplyManagementProductRepository applyManagementProductRepository;

    // 이벤트 신청 관리 - 상품 등록 신청 리스트
    public PageResponseDTO<ApplyManagementProductListDTO> getApplyProductList(PageRequestDTO pageRequestDTO) {
        return applyManagementProductRepository.applyProductList(pageRequestDTO);
    }


    // 이벤트 신청 관리 - 상품 등록 신청 상세 조회
    public ApplyManagementProductReadDTO readApplyProduct(Long productNo) {
        List<Object[]> res = applyManagementProductRepository.findWithFileByProductNo(productNo);

        if (res.isEmpty()) {
            throw new RuntimeException("해당하는 productNo 찾을 수 없습니다.");
        }

        // ProductEntity 조회
        ProductEntity product = (ProductEntity) res.get(0)[0];

        // CategoryEntity 조회
        List<CategoryEntity> categoryEntities = res.stream().map(arr -> (CategoryEntity) arr[1]).collect(Collectors.toList());

        // 카테고리 번호 목록 생성
        List<Long> categoriesNo = categoryEntities.stream().map(arr -> arr.getCategoryNo()).collect(Collectors.toList());

        // 카테고리 이름 목록 생성
        List<String> categoriesName = categoryEntities.stream().map(arr -> arr.getCategoryName()).collect(Collectors.toList());

        List<String>  attachFileNames = product.getAttachFiles().stream()
                .map(file -> file.getFileName())
                .toList();

        ApplyManagementProductReadDTO readDTO = ApplyManagementProductReadDTO.builder()
                .productNo(product.getProductNo())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
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

    // 이벤트 신청 관리 - 상품 등록 신청 상태 변경
    public Long modifyApplyProduct (ApplyManagementProductModifyDTO applyManagementProductModifyDTO) {

        Optional<ProductEntity> optionalProductEntity = applyManagementProductRepository.findById(applyManagementProductModifyDTO.getProductNo());

        if (optionalProductEntity.isEmpty()) {
            throw new RuntimeException("해당 productNo가 없습니다");
        }

        ProductEntity updateEntity = optionalProductEntity.get();
        if(applyManagementProductModifyDTO.getProductStatus() == BasicStatus.ACCEPTED) {
            updateEntity.changeProductStatusAccepted();
        }
        if (applyManagementProductModifyDTO.getProductStatus() == BasicStatus.REJECTED) {
            updateEntity.changeProductStatusRejected();
        }

        return updateEntity.getProductNo();

    }

}
