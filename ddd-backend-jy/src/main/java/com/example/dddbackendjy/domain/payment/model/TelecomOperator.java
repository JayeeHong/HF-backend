package com.example.dddbackendjy.domain.payment.model;

public enum TelecomOperator {
    SKT("SK텔레콤"),
    KT("KT"),
    LG("LG유플러스");

    private final String description;

    TelecomOperator(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}