package com.example.dddbackendjy.domain.payment.model;

public record MobilePaymentInfo(
        TelecomOperator telecomOperator,
        String residentNumber,
        String holderName
) implements PaymentInfo {
    public MobilePaymentInfo {
        validateRequired(telecomOperator, "통신사");
        validateRequired(residentNumber, "주민번호");
        validateRequired(holderName, "명의자명");

        // 주민번호 검증 (13자리)
        String cleaned = residentNumber.replaceAll("[^0-9]", "");
        if (cleaned.length() != 13) {
            throw new IllegalArgumentException("주민번호는 13자리여야 합니다: " + residentNumber);
        }
    }

    private static void validateRequired(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다");
        }
        if (value instanceof String str && str.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다");
        }
    }
}