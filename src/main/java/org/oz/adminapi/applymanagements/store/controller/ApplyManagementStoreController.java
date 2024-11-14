package org.oz.adminapi.applymanagements.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.store.dto.StoreDTO;
import org.oz.adminapi.store.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/applyManagements/store")
@RequiredArgsConstructor
@Log4j2
public class ApplyManagementStoreController {

    private final StoreService storeService;

    // 지점 신청 리스트
    @GetMapping("/list")
    public PageResponseDTO<StoreDTO> applicationList(PageRequestDTO pageRequestDTO) {
        return storeService.applicationList(pageRequestDTO);
    }

    // 상세 조회 API (storeNo로 조회)
    @GetMapping("read/{storeNo}")
    public Optional<StoreDTO> getStoreDetailByStoreNo(@PathVariable("storeNo") Long storeNo) {
        return storeService.readApplyStoreDetailByStoreNo(storeNo);
    }

    // 지점 신청서 승인 상태 변경 API
    @PutMapping("/modify")
    public ResponseEntity<Long> updateApplyStore(@RequestBody StoreDTO storeDTO) {
        return ResponseEntity.ok(storeService.applyStoreModifyStatus(storeDTO));
    }
}
