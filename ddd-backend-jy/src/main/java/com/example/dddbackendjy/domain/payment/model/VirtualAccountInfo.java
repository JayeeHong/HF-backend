package com.example.dddbackendjy.domain.payment.model;

public record VirtualAccountInfo(
        String depositorName
) implements PaymentInfo {
    public VirtualAccountInfo {
        validateRequired(depositorName, "예금주명");
    }

    private static void validateRequired(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다");
        }
    }
}