package org.oz.adminapi.event.domain;
import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.BasicEntity;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.store.domain.StoreEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admin_event")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventEntity extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_no")
    private Long eventNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_biz_no", nullable = false)
    private MakerEntity maker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_no", nullable = false)
    private StoreEntity store;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_start")
    private LocalDate eventStart;

    @Column(name = "event_end")
    private LocalDate eventEnd;

    @Enumerated(EnumType.ORDINAL)
    @Builder.Default
    @Column(name = "event_status", columnDefinition = "INT DEFAULT 0")
    private EventStatus eventStatus = EventStatus.PENDING;

    @Column(name = "spacerent_status")
    private Boolean spaceRentStatus;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    @CollectionTable(name = "admin_event_history", joinColumns = @JoinColumn(name = "event_no"))
    private Set<EventHistoryEntity> eventHistoryEntities = new HashSet<>();

    public void initHistory(EventHistoryEntity eventHistory){
        this.eventHistoryEntities.add(eventHistory);
    }
}