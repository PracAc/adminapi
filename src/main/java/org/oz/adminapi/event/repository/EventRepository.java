package org.oz.adminapi.event.repository;

import org.oz.adminapi.event.domain.EventEntity;
import org.oz.adminapi.event.dto.EventDTO;
import org.oz.adminapi.event.repository.search.EventSearch;
import org.oz.adminapi.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long>, EventSearch {

    @Query("""
    SELECT new org.oz.adminapi.event.dto.EventDTO
    (e.eventNo, e.eventName, e.eventStart, e.eventEnd, e.eventStatus, e.spaceRentStatus, m.makerName, s.storeName, e.regDate)
    FROM EventEntity e
    LEFT JOIN e.maker m
    LEFT JOIN e.store s
    WHERE e.eventNo = :eventNo
    """)
    Optional<EventDTO> readEventDetailByEventNo(@Param("eventNo") Long eventNo);

    @Query("SELECT p FROM EventProductEntity ep " +
            "JOIN ep.product p " +
            "LEFT JOIN p.attachFiles af " +
            "WHERE ep.event.eventNo = :eventNo")
    List<ProductEntity> findProductsWithImages(Long eventNo);
}
