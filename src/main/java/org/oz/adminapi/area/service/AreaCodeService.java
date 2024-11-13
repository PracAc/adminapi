package org.oz.adminapi.area.service;

import org.oz.adminapi.area.repository.AreaCodeRepository;
import org.springframework.stereotype.Service;

@Service
public class AreaCodeService {

    private final AreaCodeRepository areaCodeRepository;

    public AreaCodeService(AreaCodeRepository areaCodeRepository) {
        this.areaCodeRepository = areaCodeRepository;
    }


}
