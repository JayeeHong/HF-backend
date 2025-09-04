package com.example.dddbackendjd.exampledomain.domain.spi;

import com.example.dddbackendjd.exampledomain.domain.SubExample;

import java.util.List;

public interface SubExampleRepository {

    List<SubExample> findByExample(String exampleId);
}
