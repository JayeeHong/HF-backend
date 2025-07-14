package com.example.dddbackend.common.configuration;

import com.example.dddbackend.common.annotation.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.example.dddbackend.exampledomain",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class, })}
)
public class DomainConfiguration {
}
