package org.oz.adminapi.store.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.BasicEntity;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.localmaneger.domain.LocalManager;
import org.oz.adminapi.area.domain.AreaCode;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Table(name = "admin_store")
public class StoreEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_no")
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

    @Enumerated(EnumType.ORDINAL)
    @Builder.Default
    @Column(name = "store_approval_status", columnDefinition = "INT DEFAULT 0")
    private BasicStatus storeApprovalStatus = BasicStatus.PENDING;

    public void changeStatusPending(){
        this.storeApprovalStatus = BasicStatus.PENDING;
    }
    public void changeStatusAccepted() {
        this.storeApprovalStatus = BasicStatus.ACCEPTED;
    }
    public void changeStatusRejected() {
        this.storeApprovalStatus = BasicStatus.REJECTED;
    }

}
