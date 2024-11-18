package org.oz.adminapi.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.event.dto.EventDTO;
import org.oz.adminapi.event.dto.EventSearchDTO;
import org.oz.adminapi.event.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
@Log4j2
public class EventController {

    private final EventService eventService;

    @GetMapping("list")
    public PageResponseDTO<EventDTO> getList(PageRequestDTO pageRequestDTO) {
        return eventService.getList(pageRequestDTO);
    }

    @GetMapping("read/{eventNo}")
    public Optional<EventDTO> readEventDetailByEventNo(@PathVariable("eventNo") Long eventNo){
        return eventService.readEventDetailByEventNo(eventNo);
    }

    // 검색 API
    @GetMapping("/search")
    public PageResponseDTO<EventDTO> search(PageRequestDTO pageRequestDTO, EventSearchDTO eventSearchDTO) {
        return eventService.searchList(pageRequestDTO, eventSearchDTO);
    }
}