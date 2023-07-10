package com.base.pm.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String APP_TOPIC = "test_topic";
    public static final String GROUP_ID = "group_id";

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(APP_TOPIC, 3, (short) 1);
    }
}
