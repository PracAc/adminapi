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
    SELECT new org.oz.adminapi.event.dto.EventDTO(e.maker.makerBizNo, e.eventStart,e.eventEnd,e.eventStatus,e.spaceRentStatus) FROM EventEntity e WHERE e.eventNo = :eventNO
""")
    Optional<EventDTO> readEventDetailByEventNo(@Param("eventNO") Long eventNO);

}
