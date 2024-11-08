package org.oz.adminapi.event.dto;

import lombok.*;
import org.oz.adminapi.event.domain.EventStatus;

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
    private EventStatus eventStatus;
    private Boolean spaceRentStatus;

    public EventDTO(String makerBizNo, LocalDateTime eventStart, LocalDateTime eventEnd, EventStatus eventStatus, Boolean spaceRentStatus) {
        this.makerBizNo = makerBizNo;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventStatus = eventStatus;
        this.spaceRentStatus = spaceRentStatus;
    }
}
