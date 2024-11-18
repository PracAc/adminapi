package org.oz.adminapi.applymanagements.maker.repository;

import org.oz.adminapi.applymanagements.maker.repository.search.ApplyManagementMakerSearch;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplyManagementMakerRepository extends JpaRepository<MakerEntity, String>, ApplyManagementMakerSearch {

    @EntityGraph(attributePaths = "attachFiles")
    @Query("SELECT m FROM MakerEntity m WHERE m.makerBizNo = :makerBizNo")
    Optional<MakerEntity> findByApplyMakerBizNo(String makerBizNo);

}
