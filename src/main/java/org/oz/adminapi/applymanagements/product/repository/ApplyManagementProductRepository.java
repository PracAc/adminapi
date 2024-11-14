package org.oz.adminapi.applymanagements.product.repository;

import org.oz.adminapi.applymanagements.product.repository.search.ApplyManagementProductSearch;
import org.oz.adminapi.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplyManagementProductRepository extends JpaRepository<ProductEntity, Long>, ApplyManagementProductSearch {

    @EntityGraph(attributePaths = {"attachFiles", "maker"})
    @Query("""
        SELECT p, c FROM ProductEntity p
        LEFT JOIN ProductCategoryEntity pc ON p.productNo = pc.product.productNo
        LEFT JOIN CategoryEntity c ON pc.category.categoryNo = c.categoryNo
        WHERE p.productNo = :productNo
    """)
    List<Object[]> findWithFileByProductNo(@Param("productNo") Long ProductNo);
}
