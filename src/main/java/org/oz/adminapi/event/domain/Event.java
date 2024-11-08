package org.oz.adminapi.event.domain;
import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.BasicEntity;
import org.oz.adminapi.common.domain.BasicStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "admin_event")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_no")
    private Long eventNo;

    @Column(name = "maker_biz_no", nullable = false)
    private String makerBizNo;

    @Column(name = "store_no", nullable = false)
    private Long storeNo;

    @Column(name = "event_start")
    private LocalDateTime eventStart;

    @Column(name = "event_end")
    private LocalDateTime eventEnd;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "event_status", columnDefinition = "INT DEFAULT 0")
    private EventStatus eventStatus = EventStatus.PENDING;

    @Column(name = "spacerent_status")
    private Boolean spaceRentStatus;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "admin_event_history", joinColumns = @JoinColumn(name = "event_no"))
    private Set<EventHistory> eventHistories = new HashSet<>();

}
