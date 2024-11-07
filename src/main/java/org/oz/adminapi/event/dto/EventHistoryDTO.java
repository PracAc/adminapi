package org.oz.adminapi.event.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventHistoryDTO {
    private Integer eventStatus;
    private Boolean makerSpaceRent;
    private String rejectReason;
}


