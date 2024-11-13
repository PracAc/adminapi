package org.oz.adminapi.localmanager;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.oz.adminapi.area.repository.AreaCodeRepository;
import org.oz.adminapi.localmaneger.domain.LocalManager;
import org.oz.adminapi.localmaneger.repository.LocalManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class LocalManagerTests {

    @Autowired
    private LocalManagerRepository repository;
    @Autowired
    private AreaCodeRepository areaCodeRepository;


    @Test
    @Transactional
    @Commit
    public void localMangerDummies() {
        for (int i = 1; i <= 10 ; i++) {
            LocalManager manager = LocalManager.builder()
                    .managerName("manager"+i)
                    .areaCode(areaCodeRepository.getReferenceById("Area1"))
                    .build();
            repository.save(manager);
        }
    }
}
