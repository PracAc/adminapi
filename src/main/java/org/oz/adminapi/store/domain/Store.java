package org.oz.adminapi.store.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.localmaneger.domain.LocalManager;
import org.oz.adminapi.area.domain.AreaCode;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "admin_store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_no", referencedColumnName = "manager_no")
    private LocalManager localManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_name", referencedColumnName = "area_name")
    private AreaCode areaCode;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "store_contact", nullable = false)
    private String storeContact;

    @Column(name = "store_latitude", nullable = false)
    private String storeLatitude;

    @Column(name = "store_longitude", nullable = false)
    private String storeLongitude;

    @Column(name = "isrentavailable")
    @Builder.Default
    private Boolean isRentAvailable = Boolean.FALSE;

    @Column(name = "del_flag")
    private Boolean delFlag;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "creator")
    private String creator;
}
