package org.oz.adminapi.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oz.adminapi.event.domain.EventStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventSearchDTO {

    private String eventName;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private EventStatus eventStatus;
    private Boolean spaceRentStatus;
}