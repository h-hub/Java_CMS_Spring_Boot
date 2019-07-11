package com.hhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceConfig {
     
    @Bean
    @Profile("dev")
    public String getDevDataSource() {
        return "Running dev profile";
    }
    
    @Bean
    @Profile("prod")
    public String getProdDataSource() {
        return "Running dev profile";
    }
}

