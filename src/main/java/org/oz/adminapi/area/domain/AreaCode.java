package org.oz.adminapi.area.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.BasicEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_areacode")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaCode extends BasicEntity {
    @Id
    @Column(name = "area_name", nullable = false)
    private String areaName;
}