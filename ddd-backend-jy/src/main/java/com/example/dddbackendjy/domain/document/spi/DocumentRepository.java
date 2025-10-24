package com.example.dddbackendjy.domain.document.spi;

import com.example.dddbackendjy.domain.document.model.Document;

import java.util.Optional;

/**
 * 증빙정보 저장소 아웃바운드 포트
 * 도메인에서 외부(저장소)로 나가는 인터페이스
 */
public interface DocumentRepository {

    void save(Document document);

    Optional<Document> findById(String id);

    Optional<Document> findByMemberId(String memberId);

    boolean existsByMemberId(String memberId);
}