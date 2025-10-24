package com.example.dddbackendjy.domain.payment.model;

public enum IdentificationType {
    PERSONAL("개인", "생년월일"),
    BUSINESS("법인", "사업자등록번호");

    private final String description;
    private final String identifierName;

    IdentificationType(String description, String identifierName) {
        this.description = description;
        this.identifierName = identifierName;
    }

    public String getDescription() {
        return description;
    }

    public String getIdentifierName() {
        return identifierName;
    }
}