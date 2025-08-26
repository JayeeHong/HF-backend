package com.example.dddbackendjd.exampledomain.infrastructure.db;

import com.example.dddbackendjd.exampledomain.infrastructure.db.model.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleJpaRepository extends JpaRepository<ExampleEntity, String> {
}
