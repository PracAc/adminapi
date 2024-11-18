package org.oz.adminapi.store.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.localmaneger.domain.QLocalManager;
import org.oz.adminapi.store.domain.QStoreEntity;
import org.oz.adminapi.store.domain.StoreEntity;
import org.oz.adminapi.store.dto.StoreDTO;
import org.oz.adminapi.store.dto.StoreSearchDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class StoreSearchImpl extends QuerydslRepositorySupport implements StoreSearch {

    public StoreSearchImpl() {
        super(StoreEntity.class);
    }

    @Override
    public PageResponseDTO<StoreDTO> list(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("storeNo").descending());

        // QueryDSL의 QLocalManager 사용
        QStoreEntity store = QStoreEntity.storeEntity;
        QLocalManager localManager = QLocalManager.localManager;

        // 기본 JPQL 쿼리
        JPQLQuery<StoreEntity> query = from(store);
        query.leftJoin(localManager).on(store.localManager.eq(localManager));

        // Pagination 적용
        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<StoreDTO> dtoJPQLQuery = query
                .select(Projections.bean(StoreDTO.class,
                        store.storeNo,
                        localManager.managerName,
                        store.storeName,
                        store.storeContact,
                        store.isRentAvailable
                ))
                // 판매매장 등록이 된 지점만 조회하기위한 조건문
                .where(store.storeApprovalStatus.eq(BasicStatus.ACCEPTED)
                        .and(store.delFlag.eq(false)));

        // DTO 리스트 가져오기
        java.util.List<StoreDTO> dtoList = dtoJPQLQuery.fetch();

        long total = dtoJPQLQuery.fetchCount();

        return PageResponseDTO.<StoreDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }

    @Override
    public PageResponseDTO<StoreDTO> applicationList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("storeNo").descending());

        // QueryDSL의 QLocalManager 사용
        QStoreEntity store = QStoreEntity.storeEntity;
        QLocalManager localManager = QLocalManager.localManager;

        // 기본 JPQL 쿼리
        JPQLQuery<StoreEntity> query = from(store);
        query.leftJoin(localManager).on(store.localManager.eq(localManager));

        // Pagination 적용
        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<StoreDTO> dtoJPQLQuery = query
                .select(Projections.bean(StoreDTO.class,
                        store.storeNo,
                        localManager.managerName,
                        store.storeName,
                        store.storeContact,
                        store.isRentAvailable,
                        store.storeApprovalStatus
                ))
                // 판매매장 등록이 된 지점만 조회하기위한 조건문
                .where(store.delFlag.eq(false));

        // DTO 리스트 가져오기
        java.util.List<StoreDTO> dtoList = dtoJPQLQuery.fetch();

        long total = dtoJPQLQuery.fetchCount();

        return PageResponseDTO.<StoreDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }

    @Override
    public PageResponseDTO<StoreDTO> searchStore(PageRequestDTO pageRequestDTO, StoreSearchDTO storeSearchDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("modDate").descending());

        QStoreEntity store = QStoreEntity.storeEntity;
        QLocalManager localManager = QLocalManager.localManager;
        JPQLQuery<StoreEntity> query = from(store);
        BooleanBuilder builder = new BooleanBuilder();

        // 지점명 검색 조건 추가
        if (storeSearchDTO.getStoreName() != null && !storeSearchDTO.getStoreName().isEmpty()) {
            builder.and(store.storeName.containsIgnoreCase(storeSearchDTO.getStoreName()));
        }
        // 공간 대여 여부 조건 추가
        if (storeSearchDTO.getIsRentAvailable() != null) {
            builder.and(store.isRentAvailable.eq(storeSearchDTO.getIsRentAvailable()));
        }
        // BooleanBuilder를 사용해 where 절에 조건 추가
        query.where(builder);
        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);
        // DTO로 변환하여 결과 생성
        JPQLQuery<StoreDTO> dtoJPQLQuery = query
                .select(Projections.bean(StoreDTO.class,
                        store.storeNo,
                        localManager.managerName,
                        store.storeName,
                        store.storeContact,
                        store.isRentAvailable,
                        store.storeApprovalStatus
                ))
                // 판매매장 등록이 된 지점만 조회하기위한 조건문
                .where(store.storeApprovalStatus.eq(BasicStatus.ACCEPTED)
                        .and(store.delFlag.eq(false)));

        java.util.List<StoreDTO> dtoList = dtoJPQLQuery.fetch();
        long total = dtoJPQLQuery.fetchCount();
        return PageResponseDTO.<StoreDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }
}
