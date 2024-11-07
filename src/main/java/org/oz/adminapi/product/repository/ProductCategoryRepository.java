package org.oz.adminapi.product.repository;

import org.oz.adminapi.product.domain.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {
}
