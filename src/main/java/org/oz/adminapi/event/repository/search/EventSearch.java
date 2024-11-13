package org.oz.adminapi.event.repository.search;

import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.event.dto.EventDTO;

public interface EventSearch {
    PageResponseDTO<EventDTO> getList(PageRequestDTO pageRequestDTO);

}
