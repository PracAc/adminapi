package org.oz.adminapi.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreSearchDTO {

    private String storeName;
    private Boolean isRentAvailable;
}
