package org.oz.adminapi.store.repository.search;

import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.store.dto.StoreDTO;
import org.oz.adminapi.store.dto.StoreSearchDTO;

public interface StoreSearch {
    PageResponseDTO<StoreDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<StoreDTO> applicationList(PageRequestDTO pageRequestDTO);

    // 지점 검색
    PageResponseDTO<StoreDTO> searchStore (PageRequestDTO pageRequestDTO, StoreSearchDTO storeSearchDTO);
}
