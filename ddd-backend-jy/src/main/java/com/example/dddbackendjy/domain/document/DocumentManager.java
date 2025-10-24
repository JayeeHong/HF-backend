package com.example.dddbackendjy.domain.document;

import com.example.dddbackendjy.domain.common.DomainService;
import com.example.dddbackendjy.domain.document.api.ManageDocuments;
import com.example.dddbackendjy.domain.document.model.Document;
import com.example.dddbackendjy.domain.document.spi.DocumentRepository;

import java.util.Optional;

@DomainService
public class DocumentManager implements ManageDocuments {

    private final DocumentRepository documentRepository;

    public DocumentManager(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void registerDocument(Document document) {
        // 비즈니스 규칙: 회원당 하나의 증빙정보만 등록 가능
        if (documentRepository.existsByMemberId(document.memberId())) {
            throw new IllegalArgumentException(
                    "이미 등록된 증빙정보가 있습니다. 회원 ID: " + document.memberId()
            );
        }

        // 증빙정보 저장
        documentRepository.save(document);
    }

    @Override
    public Optional<Document> findDocumentByMemberId(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("회원 ID는 필수입니다");
        }

        return documentRepository.findByMemberId(memberId);
    }

    @Override
    public void updateDocument(Document document) {
        // 기존 증빙정보 존재 여부 확인
        Document existingDocument = documentRepository.findByMemberId(document.memberId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "등록된 증빙정보가 없습니다. 회원 ID: " + document.memberId()
                ));

        // ID 유지하면서 업데이트
        Document updatedDocument = new Document(
                existingDocument.id(),
                document.memberId(),
                document.documentType(),
                document.documentInfo(),
                existingDocument.registrationDate()
        );

        documentRepository.save(updatedDocument);
    }
}