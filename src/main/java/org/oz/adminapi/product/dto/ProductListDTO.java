package org.oz.adminapi.product.dto;

import lombok.*;
import org.oz.adminapi.common.domain.BasicStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDTO {

    private Long productNo;

    private Long categoryNo;

    private String productName;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private BasicStatus productStatus;
}