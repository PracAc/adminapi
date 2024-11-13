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

    //제작자 정보 부분
    private String makerName;
    private String makerEmail;
    private String makerPhone;

    private int makerStatus;
}
