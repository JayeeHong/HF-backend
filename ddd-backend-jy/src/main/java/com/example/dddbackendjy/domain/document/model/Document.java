package com.example.dddbackendjy.domain.document.model;

import java.time.LocalDateTime;

public record Document(
        String id,
        String memberId,
        DocumentType documentType,
        DocumentInfo documentInfo,
        LocalDateTime registrationDate
) {
    // 생성 시 유효성 검증
    public Document {
        validateRequired(memberId, "회원 ID");
        validateRequired(documentType, "증빙 타입");
        validateRequired(documentInfo, "증빙 정보");

        // DocumentType와 DocumentInfo 타입 일치 검증
        validateDocumentInfoMatchesType(documentType, documentInfo);
    }

    // 신규 증빙정보 생성 팩토리 메서드
    public static Document create(String memberId,
                                   DocumentType documentType,
                                   DocumentInfo documentInfo) {
        return new Document(
                null,
                memberId,
                documentType,
                documentInfo,
                LocalDateTime.now()
        );
    }

    // 증빙정보 업데이트
    public Document updateDocumentInfo(DocumentType documentType,
                                        DocumentInfo documentInfo) {
        return new Document(
                this.id,
                this.memberId,
                documentType,
                documentInfo,
                this.registrationDate
        );
    }

    // 유효성 검증 메서드
    private static void validateRequired(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다");
        }
        if (value instanceof String str && str.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다");
        }
    }

    /**
     * DocumentType와 DocumentInfo 타입이 일치하는지 검증
     * 헥사고날 아키텍처의 도메인 불변성 보장
     */
    private static void validateDocumentInfoMatchesType(DocumentType type, DocumentInfo info) {
        boolean isValid = switch (type) {
            case CASH_RECEIPT -> info instanceof CashReceiptInfo;
            case TAX_INVOICE -> info instanceof TaxInvoiceInfo;
            case NONE -> info instanceof NoneDocumentInfo;
        };

        if (!isValid) {
            throw new IllegalArgumentException(
                    String.format("증빙 타입(%s)과 증빙 정보 타입(%s)이 일치하지 않습니다",
                            type.getDescription(),
                            info.getClass().getSimpleName())
            );
        }
    }

    // 비즈니스 로직 메서드
    public boolean hasCashReceipt() {
        return documentType == DocumentType.CASH_RECEIPT;
    }

    public boolean hasTaxInvoice() {
        return documentType == DocumentType.TAX_INVOICE;
    }

    public boolean hasNoDocument() {
        return documentType == DocumentType.NONE;
    }
}