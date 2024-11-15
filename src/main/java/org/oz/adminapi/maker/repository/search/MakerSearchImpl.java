package org.oz.adminapi.maker.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.maker.domain.QMakerEntity;
import org.oz.adminapi.maker.dto.MakerListDTO;
import org.oz.adminapi.maker.dto.MakerSearchDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class MakerSearchImpl extends QuerydslRepositorySupport implements MakerSearch {

    public MakerSearchImpl() {super(MakerEntity.class);}

    @Override
    public PageResponseDTO<MakerListDTO> makerlist(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("modDate").descending());

        QMakerEntity maker = QMakerEntity.makerEntity;

        JPQLQuery<MakerEntity> query = from(maker);

        this.getQuerydsl().applyPagination(pageable, query);
        JPQLQuery<MakerListDTO> dtojpqlQuery = query.select(
                        Projections.bean(MakerListDTO.class,
                                maker.makerBizNo,
                                maker.makerName,
                                maker.regDate,
                                maker.modDate
                        )
                )
                .where(maker.makerStatus.eq(BasicStatus.ACCEPTED));

        java.util.List<MakerListDTO> dtoList = dtojpqlQuery.fetch();

        dtoList.forEach(log::info);

        long total = dtojpqlQuery.fetchCount();

        return PageResponseDTO.<MakerListDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }

    @Override
    public PageResponseDTO<MakerListDTO> searchMaker(PageRequestDTO pageRequestDTO,MakerSearchDTO makerSearchDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("modDate").descending());

        QMakerEntity maker = QMakerEntity.makerEntity;
        JPQLQuery<MakerEntity> query = from(maker);

        BooleanBuilder builder = new BooleanBuilder();

        // 제작자명 검색 조건 추가
        if (makerSearchDTO.getMakerName() != null && !makerSearchDTO.getMakerName().isEmpty()) {
            builder.and(maker.makerName.containsIgnoreCase(makerSearchDTO.getMakerName()));
        }

        // 승인일(modDate) 검색 조건 추가 (시작 날짜와 종료 날짜)
        if (makerSearchDTO.getStartDate() != null && makerSearchDTO.getEndDate() != null) {
            builder.and(maker.modDate.between(makerSearchDTO.getStartDate(), makerSearchDTO.getEndDate()));
        } else if (makerSearchDTO.getStartDate() != null) {
            builder.and(maker.modDate.goe(makerSearchDTO.getStartDate()));
        } else if (makerSearchDTO.getEndDate() != null) {
            builder.and(maker.modDate.loe(makerSearchDTO.getEndDate()));
        }

        // BooleanBuilder를 사용해 where 절에 조건 추가
        query.where(builder);

        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);

        // DTO로 변환하여 결과 생성
        JPQLQuery<MakerListDTO> dtoQuery = query.select(
                Projections.bean(MakerListDTO.class,
                        maker.makerName,
                        maker.modDate.as("modDate"),
                        maker.makerBizNo,
                        maker.regDate
                )
        );

        java.util.List<MakerListDTO> dtoList = dtoQuery.fetch();
        long total = dtoQuery.fetchCount();

        return PageResponseDTO.<MakerListDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();
    }
}