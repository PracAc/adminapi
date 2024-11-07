package org.oz.adminapi.event.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.event.domain.Event;

import org.oz.adminapi.event.domain.QEvent;
import org.oz.adminapi.event.domain.QEventHistory;
import org.oz.adminapi.event.dto.EventDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static org.hibernate.query.sqm.tree.SqmNode.log;


public class EventSearchImpl extends QuerydslRepositorySupport implements EventSearch {

    public EventSearchImpl() {
        super(Event.class);
    }


    @Override
    public PageResponseDTO<EventDTO> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("eventNo").descending());

        // QueryDSL의 QLocalManager 사용
        QEvent event = QEvent.event;
        QEventHistory eventHistory = QEventHistory.eventHistory;

        // 기본 JPQL 쿼리
        JPQLQuery<Event> query = from(event);

        // Pagination 적용
        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<EventDTO> dtoJPQLQuery = query
                .select(Projections.fields(EventDTO.class,
                        event.makerBizNo,
                        event.eventStart,
                        event.eventEnd,
                        event.eventStatus,
                        event.spaceRentStatus
                ));

        // DTO 리스트 가져오기
        java.util.List<EventDTO> dtoList = dtoJPQLQuery.fetch();

        dtoList.forEach(log::info);

        long total = dtoJPQLQuery.fetchCount();

        return PageResponseDTO.<EventDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }

}
