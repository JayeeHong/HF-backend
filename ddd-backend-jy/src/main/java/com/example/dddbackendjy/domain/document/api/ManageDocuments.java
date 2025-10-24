package com.example.dddbackendjy.domain.document.api;

import com.example.dddbackendjy.domain.document.model.Document;

import java.util.Optional;

/**
 * 증빙정보 관리 인바운드 포트
 * 외부(컨트롤러)에서 도메인으로 들어오는 인터페이스
 */
public interface ManageDocuments {

    // 회원의 증빙정보를 등록
    void registerDocument(Document document);

    // memberId로 증빙정보 조회
    Optional<Document> findDocumentByMemberId(String memberId);

    // 증빙정보 수정
    void updateDocument(Document document);
}