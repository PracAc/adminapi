package org.oz.adminapi.product.repository.search;

import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.product.dto.ProductListDTO;
import org.oz.adminapi.product.dto.ProductSearchDTO;

public interface ProductSearch {

    PageResponseDTO<ProductListDTO> productList (PageRequestDTO pageRequestDTO);

    // 상품 리스트 검색
    PageResponseDTO<ProductListDTO> searchProduct (PageRequestDTO pageRequestDTO, ProductSearchDTO productSearchDTO);
}
