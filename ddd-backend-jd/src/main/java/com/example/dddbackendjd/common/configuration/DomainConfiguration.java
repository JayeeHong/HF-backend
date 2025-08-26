package com.example.dddbackendjd.common.configuration;

import com.example.dddbackendjd.common.annotation.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {
                "com.example.dddbackend.exampledomain.domain",
                "com.example.dddbackend.member.domain"
        },
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class})}
)
public class DomainConfiguration {
}
