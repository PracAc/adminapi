package org.oz.adminapi.store.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.localmaneger.domain.QLocalManager;
import org.oz.adminapi.store.domain.QStoreEntity;
import org.oz.adminapi.store.domain.StoreEntity;
import org.oz.adminapi.store.dto.StoreDTO;
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
                .where(store.issale.eq(true)
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
}
