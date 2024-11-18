package org.oz.adminapi.applymanagements.maker.repository.search;

import org.oz.adminapi.applymanagements.maker.dto.ApplyManagementMakerListDTO;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;

public interface ApplyManagementMakerSearch {

    PageResponseDTO<ApplyManagementMakerListDTO> applyMakerList (PageRequestDTO pageRequestDTO);
}
