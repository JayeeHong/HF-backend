package com.example.dddbackendjy.domain.payment.model;

public enum PaymentMethod {
    CMS("CMS"),
    RCMS("실시간CMS"),
    CARD("카드"),
    MOBILE("휴대전화"),
    PAYER_PAYMENT("납부자결제"),
    VIRTUAL_ACCOUNT("가상계좌");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}