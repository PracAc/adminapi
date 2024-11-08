package org.oz.adminapi.maker.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.AttachFile;
import org.oz.adminapi.common.domain.BasicEntity;
import org.oz.adminapi.common.domain.BasicStatus;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString(exclude = {"attachFiles"}, callSuper = true)
@Table(name = "admin_maker")
// 제작자 Table
public class MakerEntity extends BasicEntity {

    @Id
    @Column(name = "maker_biz_no", nullable = false)
    private String makerBizNo;

    //제작자 정보
    @Column(name = "maker_name")
    private String makerName;
    @Column(name = "maker_email")
    private String makerEmail;
    @Column(name = "maker_phone")
    private String makerPhone;

    //제작자 주소 부분
    @Column(name = "maker_postnum")
    private String makerPostnum;
    @Column(name = "maker_addr")
    private String makerAddr;
    @Column(name = "maker_addr_detail")
    private String makerAddrDetail;

    //제작자 승인 상태
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "maker_status", columnDefinition = "INT DEFAULT 0")
    private BasicStatus makerStatus = BasicStatus.PENDING;

    @ElementCollection
    @Builder.Default
    @CollectionTable(
            name = "admin_maker_file", // 컬렉션 테이블 이름
            joinColumns = @JoinColumn(name = "maker_biz_no") // FK 컬럼 이름 설정
    )
    //제작자 포트폴리오 파일
    private Set<AttachFile> attachFiles = new HashSet<>();

    public void clearFiles(){
        attachFiles.clear();
    }
    public void updateAttachFiles(java.util.List<String> newFileNames) {
        this.attachFiles.clear();
        newFileNames.forEach(name -> attachFiles.add(new AttachFile(attachFiles.size(), name)));
    }

    public void changeStatus(BasicStatus newStatus){
          this.makerStatus = newStatus;
    }

    public void changeName(String newName){
        this.makerName = newName;
    }
    public void changeEmail(String newEmail){
        this.makerEmail = newEmail;
    }
    public void changePhone(String newPhone){
        this.makerPhone = newPhone;
    }
}
