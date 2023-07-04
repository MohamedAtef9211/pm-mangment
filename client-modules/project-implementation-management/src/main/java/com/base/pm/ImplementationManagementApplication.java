package com.base.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.base")
@EnableJpaAuditing
//@EnableDiscoveryClient
public class ImplementationManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImplementationManagementApplication.class);
    }
}
