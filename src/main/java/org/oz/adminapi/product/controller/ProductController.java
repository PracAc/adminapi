package org.oz.adminapi.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.product.dto.ProductListDTO;
import org.oz.adminapi.product.dto.ProductModifyDTO;
import org.oz.adminapi.product.dto.ProductReadDTO;
import org.oz.adminapi.product.dto.ProductSearchDTO;
import org.oz.adminapi.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;

    // 상품 리스트
    @GetMapping("/list")
    public PageResponseDTO<ProductListDTO> productList(PageRequestDTO pageRequestDTO) {

        return productService.getProductList(pageRequestDTO);
    }

    @GetMapping("/read/{productNo}")
    public ResponseEntity<ProductReadDTO> getOne(@PathVariable(name = "productNo")Long productNo){
        return ResponseEntity.ok(productService.getProductWithDetails(productNo));
    }

    @PutMapping("/modify")
    public ResponseEntity<Long> putOne( @RequestBody ProductModifyDTO updateDTO){
        return ResponseEntity.ok(productService.modifyProduct(updateDTO));
    }

    // 상품 검색
    @GetMapping("/search")
    public PageResponseDTO<ProductListDTO> searchProduct(PageRequestDTO pageRequestDTO, ProductSearchDTO productSearchDTO) {
        return productService.searchProductList(pageRequestDTO, productSearchDTO);
    }
}
