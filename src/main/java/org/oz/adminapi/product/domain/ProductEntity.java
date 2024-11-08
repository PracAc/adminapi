package org.oz.adminapi.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.AttachFile;
import org.oz.adminapi.common.domain.BasicEntity;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.maker.domain.MakerEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString(exclude = {"maker","attachFiles"}, callSuper = true)
@Table(name = "admin_product")
public class ProductEntity extends BasicEntity {

    @Id
    @Column(name = "product_no")
    // 상품코드
    private Long productNo;

    // 상품 정보
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    // 상품 승인 상태
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "product_status", columnDefinition = "INT DEFAULT 0")
    private BasicStatus productStatus = BasicStatus.PENDING;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_biz_no", nullable = false)
    private MakerEntity maker;

    @ElementCollection
    @Builder.Default
    @CollectionTable(
            name = "admin_product_file",
            joinColumns = @JoinColumn(name = "product_no")
    )
    //제작자 상품 이미지
    private Set<AttachFile> attachFiles = new HashSet<>();

    public void clearFiles(){
        attachFiles.clear();
    }
    public void updateAttachFiles(java.util.List<String> newFileNames) {
        this.attachFiles.clear();
        newFileNames.forEach(name -> attachFiles.add(new AttachFile(attachFiles.size(), name)));
    }

}
