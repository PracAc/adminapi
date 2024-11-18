package org.oz.adminapi.event.service;

import lombok.RequiredArgsConstructor;
import org.oz.adminapi.common.domain.AttachFile;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.event.domain.EventEntity;
import org.oz.adminapi.event.dto.EventDTO;
import org.oz.adminapi.event.dto.EventSearchDTO;
import org.oz.adminapi.event.repository.EventRepository;
import org.oz.adminapi.product.domain.ProductEntity;
import org.oz.adminapi.store.dto.StoreDTO;
import org.oz.adminapi.store.dto.StoreSearchDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    public PageResponseDTO<EventDTO> getList(PageRequestDTO pageRequestDTO){
        return eventRepository.getList(pageRequestDTO);
    }

    public Optional<EventDTO> readEventDetailByEventNo(Long eventNo) {
        Optional<EventDTO> eventDetail = eventRepository.readEventDetailByEventNo(eventNo);

        eventDetail.ifPresent(dto -> {
            // eventNo에 할당된 상품을 조회
            List<ProductEntity> products = eventRepository.findProductsWithImages(eventNo);

            // 상품 번호와 해당 이미지 목록을 저장할 맵
            Map<Long, List<String>> productImages = new HashMap<>();

            // 각 상품에 대해 이미지 파일 목록을 추출
            for (ProductEntity product : products) {
                List<String> fileNames = product.getAttachFiles().stream()
                        .map(AttachFile::getFileName) // 파일명만 추출
                        .collect(Collectors.toList());
                // 상품 번호와 이미지 목록을 맵에 저장
                productImages.put(product.getProductNo(), fileNames);
            }

            // 상품 정보 맵을 EventDTO에 추가
            dto.setProductImages(productImages);
        });

        return eventDetail;
    }

    // 신청 list 조회
    public PageResponseDTO<EventDTO> applicationList(PageRequestDTO pageRequestDTO) {
        return eventRepository.getApplicationList(pageRequestDTO);
    }

    // list 검색 기능
    public PageResponseDTO<EventDTO> searchList(PageRequestDTO pageRequestDTO, EventSearchDTO eventSearchDTO) {
        return eventRepository.searchEvent(pageRequestDTO, eventSearchDTO);
    }

    // 이벤트 신청 상태 변경
    public Long applyEventModifyStatus(EventDTO eventDTO) {

        Optional<EventEntity> optionalApplyEventEntity = eventRepository.findById(eventDTO.getEventNo());

        if (optionalApplyEventEntity.isEmpty()) {
            throw new RuntimeException("해당 optionalApplyEventEntity 찾을 수 없습니다.");
        }

        EventEntity updateEntity = optionalApplyEventEntity.get();
        if (eventDTO.getApprovalStatus() == BasicStatus.ACCEPTED) {
            updateEntity.changeStatusAccepted();
        }
        if (eventDTO.getApprovalStatus() == BasicStatus.REJECTED) {
            updateEntity.changeStatusRejected();
        }

        return updateEntity.getEventNo();
    }

}
