package org.oz.adminapi.event;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.oz.adminapi.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class EventTests {

    @Autowired
    private EventRepository eventRepository;

    @Test
    @Transactional
    @Commit
    public void eventDummies() {

    }
}
