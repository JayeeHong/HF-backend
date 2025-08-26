package com.example.dddbackendjd.exampledomain.infrastructure.db;

import com.example.dddbackendjd.exampledomain.domain.Example;
import com.example.dddbackendjd.exampledomain.infrastructure.db.ExampleJpaRepository;
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
