package org.oz.adminapi.maker.repository;

import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.maker.repository.search.MakerSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MakerRepository extends JpaRepository<MakerEntity,String>, MakerSearch {


    @EntityGraph(attributePaths = "attachFiles")
    @Query("SELECT m FROM MakerEntity m WHERE m.makerBizNo = :makerBizNo")
    Optional<MakerEntity> findWithFilesByMakerBizNo(@Param("makerBizNo") String makerBizId);

}
