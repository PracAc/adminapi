package org.oz.adminapi.event.dto;

import lombok.*;
import org.oz.adminapi.common.domain.AttachFile;
import org.oz.adminapi.event.domain.EventStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Setter
    private Map<Long, List<String>> productImages = new HashMap<>();

    public EventDTO(Long eventNo, String eventName, LocalDate eventStart, LocalDate eventEnd, EventStatus eventStatus, Boolean spaceRentStatus, String makerName, String storeName, LocalDateTime regDate) {
        this.eventNo = eventNo;
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventStatus = eventStatus;
        this.spaceRentStatus = spaceRentStatus;
        this.makerName = makerName;
        this.storeName = storeName;
        this.regDate = regDate;
    }
}
