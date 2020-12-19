package com.endava.crudapp.config;

import com.endava.crudapp.converter.StudentDtoToStudentConverter;
import com.endava.crudapp.converter.StudentToStudentDtoConverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StudentToStudentDtoConverter());
        registry.addConverter(new StudentDtoToStudentConverter());
    }
}

