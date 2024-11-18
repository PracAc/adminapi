package org.oz.adminapi.event.dto;

import lombok.*;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.event.domain.EventStatus;

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
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private EventStatus eventStatus;
    private Boolean spaceRentStatus;
    private String makerName; // MakerEntity의 makerName
    private String storeName; // StoreEntity의 storeName
    private LocalDateTime regDate;
    private BasicStatus approvalStatus;
    private List<EventHistoryDTO> historyList;

    @Setter
    private Map<Long, List<String>> productImages = new HashMap<>();

    // Read 조회용 생성자
    public EventDTO(Long eventNo, String eventName, LocalDateTime eventStart, LocalDateTime eventEnd, EventStatus eventStatus, Boolean spaceRentStatus, String makerName, String storeName, LocalDateTime regDate, BasicStatus approvalStatus) {
        this.eventNo = eventNo;
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventStatus = eventStatus;
        this.spaceRentStatus = spaceRentStatus;
        this.makerName = makerName;
        this.storeName = storeName;
        this.regDate = regDate;
        this.approvalStatus = approvalStatus;
    }
}
