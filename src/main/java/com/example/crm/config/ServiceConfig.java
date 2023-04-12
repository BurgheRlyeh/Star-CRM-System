package com.example.crm.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("scholarservice")
@Getter @Setter
public class ServiceConfig {
    private String property;

}
