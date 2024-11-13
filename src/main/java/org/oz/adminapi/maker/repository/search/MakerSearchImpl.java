package org.oz.adminapi.maker.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.maker.domain.QMakerEntity;
import org.oz.adminapi.maker.dto.MakerListDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class MakerSearchImpl extends QuerydslRepositorySupport implements MakerSearch {
    // private static final Logger log = LoggerFactory.getLogger(MakerSearchImpl.class);

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
}