package org.oz.adminapi.applymanagements.maker.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.applymanagements.maker.dto.ApplyManagementMakerListDTO;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.maker.domain.QMakerEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class ApplyManagementMakerSearchImpl extends QuerydslRepositorySupport implements ApplyManagementMakerSearch {

    public ApplyManagementMakerSearchImpl() {super(MakerEntity.class);}

    @Override
    public PageResponseDTO<ApplyManagementMakerListDTO> applyMakerList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("modDate").descending());

        QMakerEntity maker = QMakerEntity.makerEntity;

        JPQLQuery<MakerEntity> query = from(maker);

        this.getQuerydsl().applyPagination(pageable, query);
        JPQLQuery<ApplyManagementMakerListDTO> dtojpqlQuery = query.select(
                Projections.bean(ApplyManagementMakerListDTO.class,
                        maker.makerBizNo,
                        maker.makerName,
                        maker.modDate,
                        maker.makerStatus
                )
        );

        java.util.List<ApplyManagementMakerListDTO> dtoList = dtojpqlQuery.fetch();

        dtoList.forEach(log::info);

        long total = dtojpqlQuery.fetchCount();

        return PageResponseDTO.<ApplyManagementMakerListDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(total)
                .build();

    }
}
