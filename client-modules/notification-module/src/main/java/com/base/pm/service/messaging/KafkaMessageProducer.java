package com.base.pm.service.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.base.pm.config.KafkaConfig.APP_TOPIC;


@Service
@RequiredArgsConstructor
public class KafkaMessageProducer implements MessageProducer{

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Object message) {
        kafkaTemplate.send(APP_TOPIC, message);
    }
}