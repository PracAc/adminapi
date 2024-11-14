package org.oz.adminapi.applymanagements.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyManagementProductModifyDTO {
    private Long productNo;

    private int productStatus;
}
