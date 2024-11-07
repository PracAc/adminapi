package org.oz.adminapi.localmaneger.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.area.domain.QAreaCode;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.localmaneger.domain.LocalManager;
import org.oz.adminapi.localmaneger.domain.QLocalManager;
import org.oz.adminapi.localmaneger.dto.LocalManagerDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class LocalManagerSearchImpl extends QuerydslRepositorySupport implements LocalManagerSearch {

    public LocalManagerSearchImpl() {
        super(LocalManager.class);
    }

    @Override
    public PageResponseDTO<LocalManagerDTO> list(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("managerNo").descending());

        // QueryDSL의 QLocalManager 사용
        QLocalManager localManager = QLocalManager.localManager;
        QAreaCode areaCode = QAreaCode.areaCode;

        // 기본 JPQL 쿼리
        JPQLQuery<LocalManager> query = from(localManager);

        // Pagination 적용
        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<LocalManagerDTO> dtoJPQLQuery = query
                .leftJoin(areaCode).on(localManager.areaCode.eq(areaCode))
                .select(Projections.bean(LocalManagerDTO.class,
                        localManager.managerNo,
                        areaCode.areaName.as("areaName"),
                        localManager.managerName,
                        localManager.managerContact
                ));


        // DTO 리스트 가져오기
        java.util.List<LocalManagerDTO> dtoList = dtoJPQLQuery.fetch();

        dtoList.forEach(log::info);

        long total = dtoJPQLQuery.fetchCount();

        return PageResponseDTO.<LocalManagerDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }
}
