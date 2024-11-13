package org.oz.adminapi.event.domain;

import jakarta.persistence.*;
import lombok.*;
import org.oz.adminapi.common.domain.BasicEntity;
import org.oz.adminapi.product.domain.ProductEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString(exclude = {"event", "product"}, callSuper = true)
@Table(name = "admin_event_product")
public class EventProductEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_product_no")
    private Long eventProductNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no", nullable = false)
    // 상품 코드 (fk)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_no", nullable = false)
    // 이벤트 id (fk)
    private EventEntity event;
}
