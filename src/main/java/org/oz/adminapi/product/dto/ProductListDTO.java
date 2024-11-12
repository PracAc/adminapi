package org.oz.adminapi.product.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDTO {

    private Long productNo;

    private Long categoryNo;

    private String productName;

    private int productStatus;
}
