package com.example.dddbackendjd.exampledomain.infrastructure.external;

import com.example.dddbackend.exampledomain.domain.SubExample;
import com.example.dddbackend.exampledomain.domain.spi.SubExampleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubExampleApiClient implements SubExampleRepository {

    @Override
    public List<SubExample> findByExample(String exampleId) {
        // TODO API 조회 로직 추가 필요
        return List.of();
    }
}
