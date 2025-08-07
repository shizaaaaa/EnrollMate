package com.example.studentcourse.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    //planning to understand and use it later

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
