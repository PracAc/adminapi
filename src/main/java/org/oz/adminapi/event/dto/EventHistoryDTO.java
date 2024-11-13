package org.oz.adminapi.event.dto;

import lombok.*;
import org.oz.adminapi.common.domain.BasicStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventHistoryDTO {
    private String approvalStatus; // PENDING, APPROVED, REJECTED 등 상태
    private Boolean makerSpaceRent;
    private String rejectReason;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String creator;

    public void setApprovalStatus(BasicStatus status) {
        this.approvalStatus = status.name(); // 상태 값을 문자열로 설정
    }
}
