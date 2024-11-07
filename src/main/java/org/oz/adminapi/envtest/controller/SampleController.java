//package org.oz.adminapi.envtest.controller;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.concurrent.CompletableFuture;
//
//@RestController
//@RequiredArgsConstructor
//@Log4j2
//public class SampleController {
//
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//
//    @Value("${org.oz.v1}")
//    private String v1;
//
//    @Value("${org.oz.v2}")
//    private String v2;
//
//    @GetMapping("hello")
//    public String[] sayHello(){
//
//        CompletableFuture<SendResult<String, Object>> result = kafkaTemplate.send("topic", "say hello : " + System.currentTimeMillis());
//
//
//
//        return new String[]{"AAA","BBB","CCC"};
//    }
//}
