package com.example.dddbackendjd.exampledomain.presentation.model;

import com.example.dddbackend.exampledomain.presentation.model.SubExampleDto;

import java.util.List;

public record ExampleDto(String id, String name, List<SubExampleDto> subExamples) {
}
