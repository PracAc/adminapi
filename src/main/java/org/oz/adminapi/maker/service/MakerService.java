package org.oz.adminapi.maker.service;

import lombok.RequiredArgsConstructor;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.maker.dto.MakerListDTO;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.maker.dto.MakerModifyDTO;
import org.oz.adminapi.maker.dto.MakerReadDTO;
import org.oz.adminapi.maker.repository.MakerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class MakerService {

    private final MakerRepository makerRepository;

    public PageResponseDTO<MakerListDTO> getList(PageRequestDTO pageRequestDTO) {
        return makerRepository.makerlist(pageRequestDTO);
    }

    public MakerReadDTO readMaker(String makerBizNo) {

        Optional<MakerEntity> result = makerRepository.findWithFilesByMakerBizNo(makerBizNo);

        MakerEntity makerEntity = result.get();

        List<String> attachFileNames = makerEntity.getAttachFiles().stream()
                .map(file -> file.getFileName()).collect(Collectors.toList());

        MakerReadDTO makerReadDTO = MakerReadDTO.builder()
                .makerBizNo(makerEntity.getMakerBizNo())
                .makerName(makerEntity.getMakerName())
                .makerEmail(makerEntity.getMakerEmail())
                .makerPhone(makerEntity.getMakerPhone())
                .makerPostnum(makerEntity.getMakerPostnum())
                .makerAddr(makerEntity.getMakerAddr())
                .makerAddrDetail(makerEntity.getMakerAddrDetail())
                .attachFileNames(attachFileNames)
                .build();

        return makerReadDTO;
    }

    public String modifyMaker(MakerModifyDTO modifyDTO) {
        log.info("updateMaker---------------------------------------------");

        Optional<MakerEntity> optionalMakerEntity = makerRepository.findWithFilesByMakerBizNo(modifyDTO.getMakerBizNo());
        if (optionalMakerEntity.isEmpty()) {
            throw new RuntimeException("ssssssssssssssssss");
        }

        MakerEntity updateMakerEntity = optionalMakerEntity.get();
//        updateMakerEntity.changeStatus(modifyDTO.getMakerStatus());

        return updateMakerEntity.getMakerBizNo();
    }
}
