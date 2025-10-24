package com.example.dddbackendjy.domain.document.model;

public enum DocumentType {
    CASH_RECEIPT("현금영수증"),
    TAX_INVOICE("세금계산서"),
    NONE("미선택");

    private final String description;

    DocumentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}