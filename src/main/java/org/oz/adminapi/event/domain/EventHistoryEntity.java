package org.oz.adminapi.event.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.BasicStatus;

import java.time.LocalDateTime;

@Embeddable
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventHistoryEntity {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "approval_status", columnDefinition = "INT DEFAULT 0")
    private BasicStatus approvalStatus;

    @Column(name = "maker_spacerent")
    private Boolean makerSpaceRent;

    @Column(name = "reject_reason")
    private String rejectReason;

    @Column(name = "reg_date",updatable = false)
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "creator")
    private String creator;

    public void initDateAndCreator(LocalDateTime RegAndMod, String creator){
        this.regDate = RegAndMod;
        this.modDate = RegAndMod;
        this.creator = creator;
    }

    public void changeModDate(LocalDateTime mod){
        this.modDate = mod;
    }

    public EventHistoryEntity(Boolean rentValue){
        this.approvalStatus = BasicStatus.PENDING;
        this.makerSpaceRent = rentValue;
        this.rejectReason = "";
    }
}