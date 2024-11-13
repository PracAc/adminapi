package org.oz.adminapi.area.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.area.service.AreaCodeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/areacode")
@RequiredArgsConstructor
@Log4j2
public class AreaCodeController {

    private final AreaCodeService areaCodeService;

}
