package org.oz.adminapi.store.service;

import lombok.RequiredArgsConstructor;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.store.dto.StoreDTO;
import org.oz.adminapi.store.repository.StoreRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    // list 조회
    public PageResponseDTO<StoreDTO> list(PageRequestDTO pageRequestDTO) {
        return storeRepository.list(pageRequestDTO);
    }

    // 상세 조회 (storeNo로 조회)
    public Optional<StoreDTO> readStoreDetailByStoreNo(Long storeNo) {
        return storeRepository.findStoreByStoreNo(storeNo);
    }
}
