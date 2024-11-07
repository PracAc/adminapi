package org.oz.adminapi.event.domain;
import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.store.domain.Store;

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
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_no")
    private Long eventNo;

    //이거 조인 컬럼으로 해야함
    @Column(name = "maker_biz_no", nullable = false)
    private String makerBizNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_no", referencedColumnName = "store_no")
    private Store store;

    @Column(name = "event_start")
    private LocalDateTime eventStart;

    @Column(name = "event_end")
    private LocalDateTime eventEnd;

    @Column(name = "event_status")
    private Integer eventStatus;

    @Column(name = "del_flag")
    private Boolean delFlag;

    @Column(name = "spacerent_status")
    private Boolean spaceRentStatus;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "creator")
    private String creator;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "admin_event_history", joinColumns = @JoinColumn(name = "event_no"))
    private Set<EventHistory> eventHistories = new HashSet<>();
}
