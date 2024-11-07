package org.oz.adminapi.maker.repository.search;

import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.maker.dto.MakerListDTO;

public interface MakerSearch {

    // 제작자 리스트
    PageResponseDTO<MakerListDTO> makerlist (PageRequestDTO pageRequestDTO);
}
