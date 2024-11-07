package org.oz.adminapi.event.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventHistory {

    @Column(name = "event_status")
    private Integer eventStatus;

    @Column(name = "maker_spacerent")
    private Boolean makerSpaceRent;

    @Column(name = "del_flag")
    private Boolean delFlag;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "creator")
    private String creator;

    @Column(name = "reject_reason")
    private String rejectReason;

    // AdminEvent와의 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_no", insertable = false, updatable = false)
    private Event adminEvent;
}