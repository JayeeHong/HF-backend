package com.example.dddbackendjy.adapter.persistence.document;

import com.example.dddbackendjy.domain.document.model.Document;
import com.example.dddbackendjy.domain.document.spi.DocumentRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 증빙정보 저장소 InMemory 구현체 (Secondary Adapter)
 */
@Repository
public class InMemoryDocumentRepository implements DocumentRepository {

    private final Map<String, Document> store = new ConcurrentHashMap<>();

    @Override
    public void save(Document document) {
        store.put(document.id(), document);
    }

    @Override
    public Optional<Document> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Document> findByMemberId(String memberId) {
        return store.values().stream()
                .filter(document -> document.memberId().equals(memberId))
                .findFirst();
    }

    @Override
    public boolean existsByMemberId(String memberId) {
        return store.values().stream()
                .anyMatch(document -> document.memberId().equals(memberId));
    }
}