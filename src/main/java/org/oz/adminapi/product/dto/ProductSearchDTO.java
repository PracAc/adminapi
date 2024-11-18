package org.oz.adminapi.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchDTO {

    private String productName = "Product";

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
