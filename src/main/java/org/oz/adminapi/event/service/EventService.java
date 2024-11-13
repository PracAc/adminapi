package org.oz.adminapi.event.service;

import lombok.RequiredArgsConstructor;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.event.dto.EventDTO;
import org.oz.adminapi.event.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public PageResponseDTO<EventDTO> getList(PageRequestDTO pageRequestDTO){
        return eventRepository.getList(pageRequestDTO);
    }

    public Optional<EventDTO> readEventDetailByEventNo(Long eventNo){
        return eventRepository.readEventDetailByEventNo(eventNo);
    }
}
