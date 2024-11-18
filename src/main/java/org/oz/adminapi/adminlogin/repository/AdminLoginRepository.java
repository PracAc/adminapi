package org.oz.adminapi.adminlogin.repository;

import org.oz.adminapi.adminlogin.domain.AdminLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLoginRepository extends JpaRepository<AdminLoginEntity, String> {

}
