package com.example.dddbackend.exampledomain.infrastructure.db;

import com.example.dddbackend.exampledomain.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ExampleRepository implements com.example.dddbackend.exampledomain.domain.spi.ExampleRepository {

    private final ExampleJpaRepository exampleJpaRepository;

    public ExampleRepository(ExampleJpaRepository exampleJpaRepository) {
        this.exampleJpaRepository = exampleJpaRepository;
    }

    @Override
    public Example findById(String id) {
        var exampleEntity = exampleJpaRepository.findById(id).orElseThrow();
        return new Example(
                exampleEntity.getId(),
                exampleEntity.getId(),
                Collections.emptyList()
        );
    }
}
