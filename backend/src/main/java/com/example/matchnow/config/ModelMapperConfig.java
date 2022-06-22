package com.example.matchnow.config;

import com.example.matchnow.mapper.ProjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(projectMapper());
        return modelMapper;
    }

    @Bean
    public ProjectMapper projectMapper() {
        return new ProjectMapper();
    }
}
