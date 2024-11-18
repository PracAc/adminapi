package org.oz.adminapi.event.repository.search;

import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.event.dto.EventDTO;
import org.oz.adminapi.event.dto.EventSearchDTO;

public interface EventSearch {
    PageResponseDTO<EventDTO> getList(PageRequestDTO pageRequestDTO);

    PageResponseDTO<EventDTO> getApplicationList(PageRequestDTO pageRequestDTO);

    // 이벤트 검색
    PageResponseDTO<EventDTO> searchEvent (PageRequestDTO pageRequestDTO, EventSearchDTO eventSearchDTO);

}
