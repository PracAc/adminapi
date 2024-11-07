package org.oz.adminapi.localmaneger.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.area.domain.AreaCode;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_local_manager")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_no")
    private Long managerNo;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "manager_contact")
    private String managerContact;

    @Builder.Default
    private Boolean delFlag = Boolean.FALSE;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_name", referencedColumnName = "area_name")
    private AreaCode areaCode;
}