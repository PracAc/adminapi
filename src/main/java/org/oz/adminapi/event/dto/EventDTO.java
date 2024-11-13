package org.oz.adminapi.event.dto;

import lombok.*;
import org.oz.adminapi.event.domain.EventStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {
    private Long eventNo;
    private String eventName;
    private LocalDate eventStart;
    private LocalDate eventEnd;
    private EventStatus eventStatus;
    private Boolean spaceRentStatus;
    private String makerName; // MakerEntity의 makerName
    private String storeName; // StoreEntity의 storeName
    private LocalDateTime regDate;
}
