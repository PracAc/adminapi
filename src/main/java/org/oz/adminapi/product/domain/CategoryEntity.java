package org.oz.adminapi.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.BasicEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString(callSuper = true)
@Table(name = "admin_category")
public class CategoryEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_no", nullable = false)
    // 카테고리 id
    private Long categoryNo;

    // 카테고리명
    @Column(name = "category_name")
    private String categoryName;
}
