package org.oz.adminapi.event.dto;

import lombok.*;
import org.oz.adminapi.event.domain.EventStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDTO {
    private String makerBizNo;
    private Long storeNo;
    private LocalDate eventStart;
    private LocalDate eventEnd;
    private EventStatus eventStatus;
    private Boolean spaceRentStatus;

    public EventDTO(String makerBizNo, LocalDate eventStart, LocalDate eventEnd, EventStatus eventStatus, Boolean spaceRentStatus) {
        this.makerBizNo = makerBizNo;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventStatus = eventStatus;
        this.spaceRentStatus = spaceRentStatus;
    }
}
