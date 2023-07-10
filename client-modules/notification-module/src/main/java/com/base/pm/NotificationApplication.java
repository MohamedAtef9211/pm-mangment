package com.base.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        basePackages = "com.base",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "com\\.base\\.pm\\.common\\.client\\.base\\.jpa\\..*")
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}
