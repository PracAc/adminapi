package org.oz.adminapi.applymanagements.product.repository;

import org.oz.adminapi.product.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyManagementCategoryRepository extends JpaRepository<CategoryEntity, Long>  {
}
