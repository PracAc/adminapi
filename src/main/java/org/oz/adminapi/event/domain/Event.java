package org.oz.adminapi.event.domain;
import jakarta.persistence.*;
import lombok.*;
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

    @Column(name = "maker_biz_no", nullable = false)
    private String makerBizNo;

    @Column(name = "store_no", nullable = false)
    private Long storeNo;

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
