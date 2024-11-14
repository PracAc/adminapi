package org.oz.adminapi.applymanagements.product.repository;

import org.oz.adminapi.product.domain.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyManagementProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long>  {
}
