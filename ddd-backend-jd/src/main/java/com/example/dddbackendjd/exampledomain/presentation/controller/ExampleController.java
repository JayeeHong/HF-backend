package com.example.dddbackendjd.exampledomain.presentation.controller;

import com.example.dddbackendjd.exampledomain.application.GetExampleUseCase;
import com.example.dddbackendjd.exampledomain.presentation.model.ExampleDto;
import com.example.dddbackendjd.exampledomain.presentation.model.ExampleRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    private final GetExampleUseCase getExampleUseCase;

    public ExampleController(GetExampleUseCase getExampleUseCase) {
        this.getExampleUseCase = getExampleUseCase;
    }

    @GetMapping("/")
    public ExampleDto getExample(ExampleRequest exampleRequest) {
        return getExampleUseCase.execute(exampleRequest.exampleId());
    }
}
