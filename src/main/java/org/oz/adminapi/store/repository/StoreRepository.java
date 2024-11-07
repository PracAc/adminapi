package org.oz.adminapi.store.repository;

import org.oz.adminapi.store.domain.Store;
import org.oz.adminapi.store.dto.StoreDTO;
import org.oz.adminapi.store.repository.search.StoreSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, StoreSearch {

    // 상세 조회 쿼리 (StoreDTO에 맞춰서 필요한 칼럼을 조회)
    @Query("SELECT new org.oz.adminapi.store.dto.StoreDTO" +
            "(lm.managerName, s.storeName, s.storeContact, s.storeLatitude, s.storeLongitude, s.isRentAvailable) " +
            "FROM Store s " +
            "JOIN s.localManager lm " +
            "WHERE s.storeNo = :storeNo")
    Optional<StoreDTO> findStoreByStoreNo(Long storeNo);


}
