package org.oz.adminapi.applymanagements.product.repository.search;

import org.oz.adminapi.applymanagements.product.dto.ApplyManagementProductListDTO;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;

public interface ApplyManagementProductSearch {

    PageResponseDTO<ApplyManagementProductListDTO> applyProductList (PageRequestDTO pageRequestDTO);
}
