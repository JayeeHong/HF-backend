package com.example.dddbackendjd.exampledomain.domain;

import com.example.dddbackend.exampledomain.domain.SubExample;

import java.util.List;

public record Example(String id, String name, List<SubExample> subExamples) {
}
