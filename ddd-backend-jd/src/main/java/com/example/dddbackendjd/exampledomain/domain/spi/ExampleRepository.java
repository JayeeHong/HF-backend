package com.example.dddbackendjd.exampledomain.domain.spi;

import com.example.dddbackendjd.exampledomain.domain.Example;

public interface ExampleRepository {

    Example findById(String id);
}
