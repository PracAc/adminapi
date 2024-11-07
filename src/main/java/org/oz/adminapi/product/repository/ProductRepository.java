package org.oz.adminapi.product.repository;

import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.product.domain.ProductEntity;
import org.oz.adminapi.product.repository.search.ProductSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductSearch {

    @EntityGraph(attributePaths = {"attachFiles","maker"})
    @Query("""
        SELECT p, c.categoryName FROM ProductEntity p
        LEFT JOIN ProductCategoryEntity pc ON p.productNo = pc.product.productNo
        LEFT JOIN CategoryEntity c ON pc.category.categoryNo = c.categoryNo
        WHERE p.productNo = :productNo
    """)
    Optional<Object[]> findWithFilesByProductNo(@Param("productNo") Long ProductNo);
}
