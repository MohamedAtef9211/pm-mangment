package com.base.pm.controller;

import com.base.pm.service.messaging.KafkaMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final KafkaMessageProducer kafkaMessageProducer;

    @PostMapping("/publish")
    public String messageToTopic(@RequestParam("message") String message){
        try {
            kafkaMessageProducer.sendMessage(message);
            return "Message Sent Successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
