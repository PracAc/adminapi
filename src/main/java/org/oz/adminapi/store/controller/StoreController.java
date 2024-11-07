package org.oz.adminapi.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.store.dto.StoreDTO;
import org.oz.adminapi.store.service.StoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
@Log4j2
public class StoreController {

    private final StoreService storeService;

    // list 조회 API
    @GetMapping("/list")
    public PageResponseDTO<StoreDTO> list(PageRequestDTO pageRequestDTO) {
        return storeService.list(pageRequestDTO);
    }

    // 상세 조회 API (storeNo로 조회)
    @GetMapping("read/{storeNo}")
    public Optional<StoreDTO> getStoreDetailByStoreNo(@PathVariable("storeNo") Long storeNo) {
        return storeService.readStoreDetailByStoreNo(storeNo);
    }
}
