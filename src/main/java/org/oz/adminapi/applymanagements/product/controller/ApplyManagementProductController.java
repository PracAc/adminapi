package org.oz.adminapi.applymanagements.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.applymanagements.product.dto.ApplyManagementProductListDTO;
import org.oz.adminapi.applymanagements.product.dto.ApplyManagementProductModifyDTO;
import org.oz.adminapi.applymanagements.product.dto.ApplyManagementProductReadDTO;
import org.oz.adminapi.applymanagements.product.service.ApplyManagementProductService;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applyManagements/product")
@RequiredArgsConstructor
@Log4j2
public class ApplyManagementProductController {

    private final ApplyManagementProductService applyManagementProductService;

    // 이벤트 신청 관리 - 상품 등록 신청 리스트
    @GetMapping("/list")
    public PageResponseDTO<ApplyManagementProductListDTO> applyProductList(PageRequestDTO pageRequestDTO) {
        return applyManagementProductService.getApplyProductList(pageRequestDTO);
    }

    // 이벤트 신청 관리 - 상품 등록 신청 상세 조회
    @GetMapping("/read/{productNo}")
    public ResponseEntity<ApplyManagementProductReadDTO> getProductNoOne (@PathVariable("productNo") Long productNo) {
        return ResponseEntity.ok(applyManagementProductService.readApplyProduct(productNo));
    }

    // 이벤트 신청 관리 - 상품 등록 신청 상태 변경
    @PutMapping("/modify")
    public ResponseEntity<Long> updateApplyMaker (@RequestBody ApplyManagementProductModifyDTO applyManagementProductModifyDTO) {
        return ResponseEntity.ok(applyManagementProductService.modifyApplyProduct(applyManagementProductModifyDTO));
    }

}
