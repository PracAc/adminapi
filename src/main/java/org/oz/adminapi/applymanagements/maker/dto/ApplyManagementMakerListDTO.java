package org.oz.adminapi.applymanagements.maker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oz.adminapi.common.domain.BasicStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyManagementMakerListDTO {

    private String makerBizNo;

    private String makerName;

    private BasicStatus makerStatus;

    private LocalDateTime modDate;
}
