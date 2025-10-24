package com.example.dddbackendjy.domain.payment.model;

public enum ConsentType {
    SIMPLE_RECORDING("간편녹취"),
    SIMPLE_SIGNATURE("간편서명");

    private final String description;

    ConsentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}