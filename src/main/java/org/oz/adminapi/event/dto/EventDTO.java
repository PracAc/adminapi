package org.oz.adminapi.event.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDTO {
    private String makerBizNo;
    private Long storeNo;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private Integer eventStatus;
    private Boolean spaceRentStatus;

    public EventDTO(String makerBizNo, LocalDateTime eventStart, LocalDateTime eventEnd, Integer eventStatus, Boolean spaceRentStatus) {
        this.makerBizNo = makerBizNo;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventStatus = eventStatus;
        this.spaceRentStatus = spaceRentStatus;
    }
}
