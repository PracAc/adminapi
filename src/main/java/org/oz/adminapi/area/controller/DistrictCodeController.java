package org.oz.adminapi.area.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.area.service.DistrictCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/districtcode")
@RequiredArgsConstructor
@Log4j2
public class DistrictCodeController {

    @Autowired(required = false)
    private final DistrictCodeService districtCodeService;
}
