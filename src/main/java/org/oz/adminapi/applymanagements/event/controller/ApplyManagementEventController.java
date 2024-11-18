package org.oz.adminapi.applymanagements.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.event.dto.EventDTO;
import org.oz.adminapi.event.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/applyManagements/event")
@RequiredArgsConstructor
@Log4j2
public class ApplyManagementEventController {

    private final EventService eventService;

    // 이벤트 신청 리스트
    @GetMapping("/list")
    public PageResponseDTO<EventDTO> applicationList(PageRequestDTO pageRequestDTO) {
        return eventService.applicationList(pageRequestDTO);
    }

    // 상세 조회 API
    @GetMapping("read/{eventNo}")
    public Optional<EventDTO> readEventDetailByEventNo(@PathVariable("eventNo") Long eventNo) {
        return eventService.readEventDetailByEventNo(eventNo);
    }

    // 이벤트 신청서 승인 상태 변경 API
    @PutMapping("/modify")
    public ResponseEntity<Long> updateApplyEvent(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.applyEventModifyStatus(eventDTO));
    }
}
