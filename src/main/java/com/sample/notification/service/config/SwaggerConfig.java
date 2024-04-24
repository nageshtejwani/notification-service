package com.sample.notification.service.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public GroupedOpenApi privateApi() {
        return GroupedOpenApi.builder()
                .group("notification")
                .pathsToMatch("/notification/send")
                .build();
    }
}