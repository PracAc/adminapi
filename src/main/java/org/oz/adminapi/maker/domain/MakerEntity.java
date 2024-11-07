package org.oz.adminapi.maker.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.AttachFile;
import org.oz.adminapi.common.domain.BasicEntity;
import org.oz.adminapi.maker.dto.MakerModifyDTO;

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
    //제작자 사업자 번호 (PK)
    private String makerBizNo;

    //제작자 정보 부분
    private String makerName;
    private String makerEmail;
    private String makerPhone;

    //제작자 주소 부분
    private String makerPostnum;
    private String makerAddr;
    private String makerAddrDetail;

    //제작자 승인 상태
    private int makerStatus;

    @ElementCollection
    @Builder.Default
    @CollectionTable(
            name = "admin_maker_file", // 컬렉션 테이블 이름
            joinColumns = @JoinColumn(name = "maker_biz_no") // FK 컬럼 이름 설정
    )
    //제작자 포트폴리오 파일
    private Set<AttachFile> attachFiles = new HashSet<>();

    public void updateAttachFiles(java.util.List<String> newFileNames) {
        this.attachFiles.clear();
        newFileNames.forEach(name -> attachFiles.add(new AttachFile(attachFiles.size(), name)));
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
    public void changeStatus(int newStatus){ this.makerStatus = newStatus; }
}
