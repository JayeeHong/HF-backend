package com.example.dddbackendjd.exampledomain.application;

import com.example.dddbackend.common.annotation.UseCase;
import com.example.dddbackend.exampledomain.domain.api.AssembleAExample;
import com.example.dddbackend.exampledomain.presentation.model.ExampleDto;
import com.example.dddbackend.exampledomain.presentation.model.SubExampleDto;

@UseCase
public class GetExampleUseCase {

    private final AssembleAExample assembleAExample;

    public GetExampleUseCase(AssembleAExample assembleAExample) {
        this.assembleAExample = assembleAExample;
    }

    public ExampleDto execute(String id) {
        var example = assembleAExample.getExample(id);
        return new ExampleDto(
                example.id(),
                example.name(),
                example.subExamples()
                        .stream()
                        .map(subExample ->
                                new SubExampleDto(
                                        subExample.id(),
                                        subExample.exampleId(),
                                        subExample.name()))
                        .toList());
    }
}
