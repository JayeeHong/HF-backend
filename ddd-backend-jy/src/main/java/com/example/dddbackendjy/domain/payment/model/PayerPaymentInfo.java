package com.example.dddbackendjy.domain.payment.model;

public record PayerPaymentInfo(
        boolean cardEnabled,
        boolean accountEnabled,
        boolean simplePaymentEnabled
) implements PaymentInfo {
    public PayerPaymentInfo {
        // 최소 하나는 활성화되어야 함
        if (!cardEnabled && !accountEnabled && !simplePaymentEnabled) {
            throw new IllegalArgumentException("최소 하나의 결제 수단은 활성화되어야 합니다");
        }
    }
}