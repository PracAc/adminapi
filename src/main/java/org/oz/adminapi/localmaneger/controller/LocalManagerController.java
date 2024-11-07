package org.oz.adminapi.localmaneger.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.localmaneger.dto.LocalManagerDTO;
import org.oz.adminapi.localmaneger.service.LocalManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/localmanager")
@RequiredArgsConstructor
@Log4j2
public class LocalManagerController {

    private final LocalManagerService localManagerService;

    @GetMapping("list")
    public PageResponseDTO<LocalManagerDTO> list(PageRequestDTO pageRequestDTO){
        return localManagerService.getList(pageRequestDTO);
    }

}
