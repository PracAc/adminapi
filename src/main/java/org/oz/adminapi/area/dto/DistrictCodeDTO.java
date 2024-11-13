package org.oz.adminapi.area.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class DistrictCodeDTO {
    private Long districtId;
    private String areaName; // 외래키로 사용
    private String districtName;
    private Boolean delFlag;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String creator;
}
