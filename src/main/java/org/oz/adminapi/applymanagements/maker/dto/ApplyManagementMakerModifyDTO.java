package org.oz.adminapi.applymanagements.maker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oz.adminapi.common.domain.BasicStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyManagementMakerModifyDTO {
    private String makerBizNo;

    private BasicStatus makerStatus;
}
