package org.oz.adminapi.areacode;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.oz.adminapi.area.domain.AreaCode;
import org.oz.adminapi.area.domain.DistrictCode;
import org.oz.adminapi.area.repository.AreaCodeRepository;
import org.oz.adminapi.area.repository.DistrictCodeRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class AreaCodeTests {

    @Autowired
    private AreaCodeRepository areaCodeRepository;

    @Autowired
    private DistrictCodeRepositroy districtCodeRepositroy;

    @Test
    @Transactional
    @Commit
    public void areaDummies() {
        for (int i = 1; i <= 10 ; i++) {
            AreaCode areaCode = AreaCode.builder()
                    .areaName("Area" + i)
                    .build();
            areaCodeRepository.save(areaCode);
        }
    }

    @Test
    @Transactional
    @Commit
    public void districtDummies() {
        for (int i = 1; i <= 10 ; i++) {
            DistrictCode districtCode = DistrictCode.builder()
                    .districtName("District" + i)
                    .areaCode(areaCodeRepository.getReferenceById("Area1"))
                    .build();

            districtCodeRepositroy.save(districtCode);
        }
    }
}
