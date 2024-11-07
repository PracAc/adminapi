package org.oz.adminapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
public class AdminapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminapiApplication.class, args);
    }

}
