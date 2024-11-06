package org.oz.adminapi.event.service;

import lombok.RequiredArgsConstructor;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.event.dto.EventDTO;
import org.oz.adminapi.event.repository.EventRepository;
import org.oz.adminapi.localmaneger.repository.LocalManagerRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public PageResponseDTO<EventDTO> getList(PageRequestDTO pageRequestDTO){
        return eventRepository.list(pageRequestDTO);
    }
}
