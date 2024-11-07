package org.oz.adminapi.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.AttachFile;
import org.oz.adminapi.common.domain.BasicEntity;
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
    // 상품코드
    private Long productNo;

    // 상품 정보
    private String productName;
    private String productDescription;

    // 상품 승인 상태
    private int productStatus;

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

    public void addFile(String filename){
        attachFiles.add(new AttachFile(attachFiles.size(), filename));
    }

    public void clearFiles(){
        attachFiles.clear();
    }

    public void changeStatus(int newStatus){
        this.productStatus = newStatus;
    }
}
