package org.oz.adminapi.event.repository;

import org.oz.adminapi.event.domain.EventEntity;
import org.oz.adminapi.event.dto.EventDTO;
import org.oz.adminapi.event.repository.search.EventSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
