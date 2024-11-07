package org.oz.adminapi.maker.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.maker.dto.MakerModifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class MakerTests {

    @Autowired
    private MakerRepository makerRepository;

    @Test
    @Transactional
    @Commit
    public void testDummies(){

        for (int i = 1; i <= 150; i++) {
            MakerEntity maker = MakerEntity.builder()
                    .makerBizNo( i + "23-45-67890")
                    .makerName("choi" + i)
                    .makerEmail("jiho"+ i +"@gmail.com")
                    .makerPhone("010-1234-5678")
                    .makerPostnum("47240")
                    .makerAddr("addr" + i)
                    .makerAddrDetail("addr detail" + i)
                    .makerStatus(0)
                    .build();
            makerRepository.save(maker);
            log.info(maker.toString());
        }//end for

    }

    @Test
    public void readOne() {
        String makerBizNo = "123-45-67890";
        MakerEntity entity = makerRepository.findWithFilesByMakerBizNo(makerBizNo).get();

        log.info(entity);
    }

    @Test
    @Transactional
    @Commit
    public void testModify(){

        log.info("updateMaker---------------------------------------------");

        MakerModifyDTO modifyDTO = MakerModifyDTO.builder()
                .makerBizNo("123-45-67890")
                .makerName("Kim1")
                .makerEmail("kim1@gmail.com")
                .makerPhone("010-4567-8901")
                .build();

        Optional<MakerEntity> optionalMakerEntity = makerRepository.findWithFilesByMakerBizNo(modifyDTO.getMakerBizNo());

        MakerEntity updateMakerEntity = optionalMakerEntity.get();
        updateMakerEntity.changeName(modifyDTO.getMakerName());
        updateMakerEntity.changeEmail(modifyDTO.getMakerEmail());
        updateMakerEntity.changePhone(modifyDTO.getMakerPhone());
    }


}
