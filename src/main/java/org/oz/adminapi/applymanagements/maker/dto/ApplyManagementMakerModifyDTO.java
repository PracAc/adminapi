package org.oz.adminapi.applymanagements.maker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyManagementMakerModifyDTO {
    private String makerBizNo;

    private int makerStatus;
}
