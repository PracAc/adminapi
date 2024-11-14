package org.oz.adminapi.store.repository.search;

import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.store.dto.StoreDTO;

public interface StoreSearch {
    PageResponseDTO<StoreDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<StoreDTO> applicationList(PageRequestDTO pageRequestDTO);
}
