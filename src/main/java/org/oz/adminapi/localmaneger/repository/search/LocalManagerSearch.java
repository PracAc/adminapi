package org.oz.adminapi.localmaneger.repository.search;

import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.localmaneger.dto.LocalManagerDTO;

public interface LocalManagerSearch {
    PageResponseDTO<LocalManagerDTO> list(PageRequestDTO pageRequestDTO);
}
