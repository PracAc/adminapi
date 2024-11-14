package org.oz.adminapi.applymanagements.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oz.adminapi.common.domain.BasicStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyManagementProductListDTO {

    private Long productNo;

    private Long categoryNo;

    private String productName;

    private LocalDateTime regDate;

    private BasicStatus productStatus;
}
