package org.oz.adminapi.area.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_districtcode")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_no")
    private Long districtNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_name", referencedColumnName = "area_name")
    private AreaCode areaCode;

    private String districtName;
    @Builder.Default
    private Boolean delFlag = Boolean.FALSE;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String creator;
}