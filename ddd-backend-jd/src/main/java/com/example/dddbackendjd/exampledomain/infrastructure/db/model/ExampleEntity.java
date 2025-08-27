package com.example.dddbackendjd.exampledomain.infrastructure.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class ExampleEntity {

    @Id
    private String id;

    private String name;
}
