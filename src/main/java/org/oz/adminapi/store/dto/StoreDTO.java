package org.oz.adminapi.store.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.oz.adminapi.common.domain.BasicStatus;

@Data
@NoArgsConstructor
public class StoreDTO {

    private Long storeNo;
    private String managerName;
    private String areaName;
    private String storeName;
    private String storeContact;
    private String storeLatitude;
    private String storeLongitude;
    private Boolean isRentAvailable;
    private BasicStatus storeApprovalStatus;

    // Read 조회용 생성자
    public StoreDTO(String managerName, String storeName, String storeContact, String storeLatitude, String storeLongitude, Boolean isRentAvailable) {
        this.managerName = managerName;
        this.storeName = storeName;
        this.storeContact = storeContact;
        this.storeLatitude = storeLatitude;
        this.storeLongitude = storeLongitude;
        this.isRentAvailable = isRentAvailable;
    }

    // 신청 Read 조회용 생성자
    public StoreDTO(String managerName, String storeName, String storeContact, String storeLatitude, String storeLongitude, Boolean isRentAvailable, BasicStatus storeApprovalStatus) {
        this.managerName = managerName;
        this.storeName = storeName;
        this.storeContact = storeContact;
        this.storeLatitude = storeLatitude;
        this.storeLongitude = storeLongitude;
        this.isRentAvailable = isRentAvailable;
        this.storeApprovalStatus = storeApprovalStatus;
    }
}
