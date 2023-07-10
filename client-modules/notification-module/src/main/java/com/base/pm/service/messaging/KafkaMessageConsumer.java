package com.base.pm.service.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.base.pm.config.KafkaConfig.GROUP_ID;
import static com.base.pm.config.KafkaConfig.APP_TOPIC;

@Service
@Slf4j
public class KafkaMessageConsumer implements MessageConsumer{
    @KafkaListener(topics = APP_TOPIC ,groupId = GROUP_ID)
    public void consumeMessage(Object message){
        log.info("inside consumeMessage and message is {}",message);
    }
}
