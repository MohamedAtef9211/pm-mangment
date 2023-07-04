package com.base.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.base")
@EnableJpaAuditing
public class ProjectsCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectsCommonApplication.class);
    }
}
