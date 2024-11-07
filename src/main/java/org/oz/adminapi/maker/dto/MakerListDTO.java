package org.oz.adminapi.maker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakerListDTO {

    private String makerBizNo;

    private String makerName;

    private int makerStatus;

    private LocalDateTime lastModifiedDate;

}
