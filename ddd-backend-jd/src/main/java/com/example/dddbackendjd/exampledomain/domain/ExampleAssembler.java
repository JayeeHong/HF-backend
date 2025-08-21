package com.example.dddbackendjd.exampledomain.domain;

import com.example.dddbackend.common.annotation.DomainService;
import com.example.dddbackend.exampledomain.domain.api.AssembleAExample;
import com.example.dddbackend.exampledomain.domain.spi.ExampleRepository;
import com.example.dddbackend.exampledomain.domain.spi.SubExampleRepository;

@DomainService
public class ExampleAssembler implements AssembleAExample {

    private final ExampleRepository exampleRepository;
    private final SubExampleRepository subExampleRepository;

    public ExampleAssembler(ExampleRepository exampleRepository, SubExampleRepository subExampleRepository) {
        this.exampleRepository = exampleRepository;
        this.subExampleRepository = subExampleRepository;
    }

    @Override
    public Example getExample(String id) {
        var example = exampleRepository.findById(id);
        var subExamples = subExampleRepository.findByExample(example.id());

        return new Example(
                example.id(),
                example.name(),
                subExamples
        );
    }
}
