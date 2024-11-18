package org.oz.adminapi.applymanagements.maker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.applymanagements.maker.dto.ApplyManagementMakerListDTO;
import org.oz.adminapi.applymanagements.maker.dto.ApplyManagementMakerModifyDTO;
import org.oz.adminapi.applymanagements.maker.dto.ApplyManagementMakerReadDTO;
import org.oz.adminapi.applymanagements.maker.service.ApplyManagementMakerService;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applyManagements/maker")
@RequiredArgsConstructor
@Log4j2
public class ApplyManagementMakerController {

    private final ApplyManagementMakerService applyManagementMakerService;

    // 제작자 신청 리스트
    @GetMapping("/list")
    public PageResponseDTO<ApplyManagementMakerListDTO> applyMakerList(PageRequestDTO pageRequestDTO) {
        return applyManagementMakerService.getApplyMakerList(pageRequestDTO);
    }

    // 제작자 신청 상세 조회
    @GetMapping("/read/{makerBizNo}")
    public ResponseEntity<ApplyManagementMakerReadDTO> getApplyMakerOne(@PathVariable(name = "makerBizNo") String makerBizNo) {
        return ResponseEntity.ok(applyManagementMakerService.readApplyMaker(makerBizNo));
    }

    // 제작자 신청서 승인 상태 변경
    @PutMapping("/modify")
    public ResponseEntity<String> updateApplyMaker(@RequestBody ApplyManagementMakerModifyDTO applyManagementMakerModifyDTO) {
        return ResponseEntity.ok(applyManagementMakerService.applyMakerModifyStatus(applyManagementMakerModifyDTO));
    }

}
