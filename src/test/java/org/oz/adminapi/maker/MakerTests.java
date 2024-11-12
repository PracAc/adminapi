package org.oz.adminapi.maker;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.oz.adminapi.common.domain.AttachFile;
import org.oz.adminapi.common.domain.BasicStatus;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.maker.dto.MakerModifyDTO;
import org.oz.adminapi.maker.repository.MakerRepository;
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
    public void testFileDummies(){

        List<String> attachFiles = new ArrayList<>();
        attachFiles.add("aaa.jpg");
        attachFiles.add("vvv.jpg");
        attachFiles.add("ccc.jpg");


        for (int i = 1; i <= 150; i++) {
            MakerEntity maker = MakerEntity.builder()
                    .makerBizNo( i + "23-45-67890")
                    .makerName("choi" + i)
                    .makerEmail("jiho"+ i +"@gmail.com")
                    .makerPhone("010-1234-5678")
                    .makerPostnum("47240")
                    .makerAddr("addr" + i)
                    .makerAddrDetail("addr detail" + i)
                    .makerStatus(BasicStatus.PENDING)
                    .build();

            maker.updateAttachFiles(attachFiles);
            makerRepository.save(maker);
            log.info(maker.toString());
        }//end for
    }
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
                    .makerStatus(BasicStatus.PENDING)
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
                .makerStatus(1)
                .build();

        int newStatus = modifyDTO.getMakerStatus();

        log.info(modifyDTO);

        Optional<MakerEntity> optionalMakerEntity = makerRepository.findById(modifyDTO.getMakerBizNo());

        MakerEntity updateMakerEntity = optionalMakerEntity.get();

        if (newStatus == 1){
            log.info("ACCEPTED");
            updateMakerEntity.changeStatusAccepted();
        }
        if (newStatus == 2){
            log.info("REJECTED");
            updateMakerEntity.changeStatusRejected();
        }

        log.info(updateMakerEntity);
    }

    @Test
    @Transactional
    @Commit
    public void testDelFlag(){
        String makerBizNo = "123-45-67890";

        Optional<MakerEntity> optionalMakerEntity = makerRepository.findById(makerBizNo);
        MakerEntity makerEntity = optionalMakerEntity.get();

        makerEntity.changeDelFlag(false);

        log.info(makerEntity);
    }
}
