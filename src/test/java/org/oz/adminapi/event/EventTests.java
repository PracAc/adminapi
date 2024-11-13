package org.oz.adminapi.event;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.oz.adminapi.event.domain.EventEntity;
import org.oz.adminapi.event.domain.EventHistoryEntity;
import org.oz.adminapi.event.domain.EventStatus;
import org.oz.adminapi.event.repository.EventRepository;
import org.oz.adminapi.maker.repository.MakerRepository;
import org.oz.adminapi.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class EventTests {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private MakerRepository makerRepository;

    @Test
    @Transactional
    @Commit
    public void eventDummies() {

        // 이벤트 생성시 필요
        // 이벤트 값이 생성, 이벤트 히스토리 생성
        // 스토어 Id 3L , 제작자 123-45-67890

        EventEntity eventEntity = EventEntity.builder()
                .eventName("제작자 안상규")
                .maker(makerRepository.getReferenceById("123-45-67890"))
                .store(storeRepository.getReferenceById(3L))
                .eventStart(LocalDate.of(2024,11,12))
                .eventEnd(LocalDate.of(2024,12,12))
                .eventStatus(EventStatus.PENDING)
                .spaceRentStatus(true)
                .build();

        // regDate생성을 위한 Save
        eventRepository.save(eventEntity);

        // regDate,ModDate생성후 DB입력
        EventHistoryEntity eventHistory = new EventHistoryEntity(true);
        eventHistory.initDateAndCreator(eventEntity.getRegDate(),eventEntity.getCreator());

        log.info(eventEntity.getRegDate());

        eventEntity.initHistory(eventHistory);
    }

}
