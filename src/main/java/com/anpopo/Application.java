package com.anpopo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing  // JPA Auditing 활성화
@SpringBootApplication
public class Application {

    private static final String PROPERTIES =
            "spring.config.location="
                    +"classpath:/application.yml";

    public static void main(String[] args) {

        new SpringApplicationBuilder(Application.class)
                .properties(PROPERTIES)
                .run(args);
    }
}
