package org.oz.adminapi.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oz.adminapi.area.domain.AreaCode;

import java.time.LocalDateTime;

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
    private Boolean issale;

    public StoreDTO(Long storeNo, String storeName, String managerName, String storeContact, Boolean isRentAvailable) {
        this.storeNo = storeNo;
        this.storeName = storeName;
        this.managerName = managerName;
        this.storeContact = storeContact;
        this.isRentAvailable = isRentAvailable;
    }

    // Read 조회용 생성자
    public StoreDTO(String managerName, String storeName, String storeContact, String storeLatitude, String storeLongitude, Boolean isRentAvailable) {
        this.managerName = managerName;
        this.storeName = storeName;
        this.storeContact = storeContact;
        this.storeLatitude = storeLatitude;
        this.storeLongitude = storeLongitude;
        this.isRentAvailable = isRentAvailable;
    }
}
