package org.oz.adminapi.area.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.BasicEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_districtcode")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictCode extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_no")
    private Long districtNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_name", referencedColumnName = "area_name")
    private AreaCode areaCode;

    private String districtName;
}