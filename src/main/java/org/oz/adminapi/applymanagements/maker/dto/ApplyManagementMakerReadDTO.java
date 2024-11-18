package org.oz.adminapi.applymanagements.maker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyManagementMakerReadDTO {

    private String makerBizNo;

    //제작자 정보 부분
    private String makerName;
    private String makerEmail;
    private String makerPhone;

    //제작자 주소 부분
    private String makerPostnum;
    private String makerAddr;
    private String makerAddrDetail;

    //제작자 포트폴리오 파일
    private List<String> attachFileNames;

    private Boolean delFlag;

    private LocalDateTime createDate;

    private LocalDateTime lastModifiedDate;

    private String creatorName;
}
