package org.oz.adminapi.localmaneger.repository;

import org.oz.adminapi.localmaneger.domain.LocalManager;
import org.oz.adminapi.localmaneger.repository.search.LocalManagerSearch;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LocalManagerRepository extends JpaRepository<LocalManager, Long>, LocalManagerSearch {
}
