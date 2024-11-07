package org.oz.adminapi.area.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_areacode")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaCode {
    @Id
    @Column(name = "area_name", nullable = false)
    private String areaName;

    @Builder.Default
    private Boolean delFlag = Boolean.FALSE;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String creator;
}