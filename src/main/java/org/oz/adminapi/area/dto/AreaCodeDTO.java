package org.oz.adminapi.area.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class AreaCodeDTO {
    private String areaName;
    private Boolean delFlag;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String creator;
}
