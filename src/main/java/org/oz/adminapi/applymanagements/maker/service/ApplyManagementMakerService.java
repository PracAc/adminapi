package org.oz.adminapi.applymanagements.maker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.applymanagements.maker.dto.ApplyManagementMakerListDTO;
import org.oz.adminapi.applymanagements.maker.dto.ApplyManagementMakerModifyDTO;
import org.oz.adminapi.applymanagements.maker.dto.ApplyManagementMakerReadDTO;
import org.oz.adminapi.applymanagements.maker.repository.ApplyManagementMakerRepository;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ApplyManagementMakerService {

    private final ApplyManagementMakerRepository applyManagementMakerRepository;

    // 이벤트 신청 관리 - 제작자 신청 리스트
    public PageResponseDTO<ApplyManagementMakerListDTO> getApplyMakerList(PageRequestDTO pageRequestDTO) {
        return applyManagementMakerRepository.applyMakerList(pageRequestDTO);
    }

    // 이벤트 신청 관리 - 제작자 신청 상세 조회
    public ApplyManagementMakerReadDTO readApplyMaker(String makerBizNo) {

        Optional<MakerEntity> result = applyManagementMakerRepository.findByApplyMakerBizNo(makerBizNo);

        MakerEntity makerEntity = result.get();

        List<String> attachFileNames = makerEntity.getAttachFiles().stream()
                .map(file -> file.getFileName()).collect(Collectors.toList());


        ApplyManagementMakerReadDTO applyMakerDTO = ApplyManagementMakerReadDTO.builder()
                .makerBizNo(makerEntity.getMakerBizNo())
                .makerName(makerEntity.getMakerName())
                .makerEmail(makerEntity.getMakerEmail())
                .makerPhone(makerEntity.getMakerPhone())
                .makerPostnum(makerEntity.getMakerPostnum())
                .makerAddr(makerEntity.getMakerAddr())
                .makerAddrDetail(makerEntity.getMakerAddrDetail())
                .attachFileNames(attachFileNames)
                .build();

        return applyMakerDTO;
    }

    // 이벤트 신청 관리 - 제작자 신청 상태 변경
    public String applyMakerModifyStatus(ApplyManagementMakerModifyDTO applyManagementMakerModifyDTO) {

        Optional<MakerEntity> optionalApplyMakerEntity = applyManagementMakerRepository.findById(applyManagementMakerModifyDTO.getMakerBizNo());

        if (optionalApplyMakerEntity.isEmpty()) {
            throw new RuntimeException("해당 optionalApplyMakerEntity 찾을 수 없습니다.");
        }

        MakerEntity updateEntity = optionalApplyMakerEntity.get();
        if (applyManagementMakerModifyDTO.getMakerStatus() == BasicStatus.ACCEPTED) {
            updateEntity.changeStatusAccepted();
        }
        if (applyManagementMakerModifyDTO.getMakerStatus() == BasicStatus.REJECTED){
            updateEntity.changeStatusRejected();
        }

        return updateEntity.getMakerBizNo();
    }

}
