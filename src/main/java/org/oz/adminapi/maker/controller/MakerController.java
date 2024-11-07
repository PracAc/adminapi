package org.oz.adminapi.maker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.maker.dto.MakerListDTO;
import org.oz.adminapi.maker.service.MakerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maker")
@RequiredArgsConstructor
@Log4j2
public class MakerController {

    private final MakerService makerService;

    // 제작자 리스트
    @GetMapping("/list")
    public PageResponseDTO<MakerListDTO> makerList(PageRequestDTO pageRequestDTO) {

        return makerService.getList(pageRequestDTO);
    }
}
