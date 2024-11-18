package org.oz.adminapi.event.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.event.domain.EventEntity;

import org.oz.adminapi.event.domain.QEventEntity;
import org.oz.adminapi.event.dto.EventDTO;
import org.oz.adminapi.event.dto.EventSearchDTO;
import org.oz.adminapi.maker.domain.QMakerEntity;
import org.oz.adminapi.store.domain.QStoreEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class EventSearchImpl extends QuerydslRepositorySupport implements EventSearch {

    public EventSearchImpl() {
        super(EventEntity.class);
    }

    @Override
    public PageResponseDTO<EventDTO> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("eventNo").descending());

        // QueryDSL의 QLocalManager 사용
        QEventEntity event = QEventEntity.eventEntity;
        QMakerEntity maker = QMakerEntity.makerEntity;
        QStoreEntity store = QStoreEntity.storeEntity;

        // 기본 JPQL 쿼리
        JPQLQuery<EventEntity> query = from(event);
        query.leftJoin(maker).on(event.maker.eq(maker));
        query.leftJoin(store).on(event.store.eq(store));

        // Pagination 적용
        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<EventDTO> dtoJPQLQuery = query
                .select(Projections.bean(EventDTO.class,
                        event.eventNo,
                        event.eventName,
                        event.eventStart,
                        event.eventEnd,
                        event.eventStatus,
                        event.spaceRentStatus,
                        maker.makerName.as("makerName"),
                        store.storeName.as("storeName")
                ))
                .where(event.approvalStatus.eq(BasicStatus.ACCEPTED)
                        .and(store.delFlag.eq(false)));

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

    @Override
    public PageResponseDTO<EventDTO> getApplicationList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("eventNo").descending());

        // QueryDSL의 QLocalManager 사용
        QEventEntity event = QEventEntity.eventEntity;
        QMakerEntity maker = QMakerEntity.makerEntity;
        QStoreEntity store = QStoreEntity.storeEntity;

        // 기본 JPQL 쿼리
        JPQLQuery<EventEntity> query = from(event);
        query.leftJoin(maker).on(event.maker.eq(maker));
        query.leftJoin(store).on(event.store.eq(store));

        // Pagination 적용
        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<EventDTO> dtoJPQLQuery = query
                .select(Projections.bean(EventDTO.class,
                        event.eventNo,
                        event.eventName,
                        event.eventStart,
                        event.eventEnd,
                        event.eventStatus,
                        event.spaceRentStatus,
                        event.approvalStatus,
                        event.regDate,
                        maker.makerName.as("makerName"),
                        store.storeName.as("storeName")
                ))
                .where(event.delFlag.eq(false));

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

    @Override
    public PageResponseDTO<EventDTO> searchEvent(PageRequestDTO pageRequestDTO, EventSearchDTO eventSearchDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("eventNo").descending());

        QEventEntity event = QEventEntity.eventEntity;
        QMakerEntity maker = QMakerEntity.makerEntity;
        QStoreEntity store = QStoreEntity.storeEntity;

        // 기본 JPQL 쿼리
        JPQLQuery<EventEntity> query = from(event);
        query.leftJoin(maker).on(event.maker.eq(maker));
        query.leftJoin(store).on(event.store.eq(store));

        BooleanBuilder builder = new BooleanBuilder();

        // 검색 조건 추가
        // 이벤트 이름
        if (eventSearchDTO.getEventName() != null && !eventSearchDTO.getEventName().isEmpty()) {
            builder.and(event.eventName.containsIgnoreCase(eventSearchDTO.getEventName()));
        }
        // 이벤트 시작날짜
        if (eventSearchDTO.getEventStart() != null) {
            builder.and(event.eventStart.goe(eventSearchDTO.getEventStart()));
        }
        // 이벤트 종료날짜
        if (eventSearchDTO.getEventEnd() != null) {
            builder.and(event.eventEnd.loe(eventSearchDTO.getEventEnd()));
        }
        // 이벤트 진행상태
        if (eventSearchDTO.getEventStatus() != null) {
            builder.and(event.eventStatus.eq(eventSearchDTO.getEventStatus()));
        }
        // 공간대여 여부
        if (eventSearchDTO.getSpaceRentStatus() != null) {
            builder.and(event.spaceRentStatus.eq(eventSearchDTO.getSpaceRentStatus()));
        }

        query.where(builder);

        // Pagination 적용
        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<EventDTO> dtoJPQLQuery = query
                .select(Projections.bean(EventDTO.class,
                        event.eventNo,
                        event.eventName,
                        event.eventStart,
                        event.eventEnd,
                        event.eventStatus,
                        event.spaceRentStatus,
                        maker.makerName.as("makerName"),
                        store.storeName.as("storeName")
                ))
                .where(event.approvalStatus.eq(BasicStatus.ACCEPTED)
                        .and(store.delFlag.eq(false)));

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
