package com.test.validate;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ConfigEnvTypeProperties.class)
public class ValidationConfiguration {

    @Bean
    public ConfigEnvTypeProperties configProperties() {
        return new ConfigEnvTypeProperties();
    }
}