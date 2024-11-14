package org.oz.adminapi.store.service;

import lombok.RequiredArgsConstructor;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.store.domain.StoreEntity;
import org.oz.adminapi.store.dto.StoreDTO;
import org.oz.adminapi.store.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
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

    // 신청 list 조회
    public PageResponseDTO<StoreDTO> applicationList(PageRequestDTO pageRequestDTO) {
        return storeRepository.applicationList(pageRequestDTO);
    }

    // 상세 조회 (storeNo로 조회)
    public Optional<StoreDTO> readApplyStoreDetailByStoreNo(Long storeNo) {
        return storeRepository.findApplyStoreByStoreNo(storeNo);
    }

    // 이벤트 신청 관리 - 지점 신청 상태 변경
    public Long applyStoreModifyStatus(StoreDTO storeDTO) {

        Optional<StoreEntity> optionalApplyStoreEntity = storeRepository.findById(storeDTO.getStoreNo());

        if (optionalApplyStoreEntity.isEmpty()) {
            throw new RuntimeException("해당 optionalApplyStoreEntity 찾을 수 없습니다.");
        }

        StoreEntity updateEntity = optionalApplyStoreEntity.get();
        if (storeDTO.getStoreApprovalStatus() == BasicStatus.ACCEPTED) {
            updateEntity.changeStatusAccepted();
        }
        if (storeDTO.getStoreApprovalStatus() == BasicStatus.REJECTED){
            updateEntity.changeStatusRejected();
        }

        return updateEntity.getStoreNo();
    }
}
