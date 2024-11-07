package org.oz.adminapi.area.repository;

import org.oz.adminapi.area.domain.AreaCode;
import org.oz.adminapi.area.repository.search.AreaCodeSearch;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AreaCodeRepository extends JpaRepository<AreaCode, String>, AreaCodeSearch {

}
