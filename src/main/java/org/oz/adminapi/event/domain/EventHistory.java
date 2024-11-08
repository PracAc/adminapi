package org.oz.adminapi.event.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.BasicEntity;
import org.oz.adminapi.common.domain.BasicStatus;

import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventHistory extends BasicEntity {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "approval_status", columnDefinition = "INT DEFAULT 0")
    private BasicStatus approvalStatus = BasicStatus.PENDING;

    @Column(name = "maker_spacerent")
    private Boolean makerSpaceRent;

    @Column(name = "reject_reason")
    private String rejectReason;

    // AdminEvent와의 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_no", insertable = false, updatable = false)
    private Event adminEvent;
}