package com.example.dddbackendjy.domain.payment.model;

import java.time.LocalDateTime;

public record Payment(
        String id,
        String memberId,
        PaymentMethod paymentMethod,
        PaymentInfo paymentInfo,
        LocalDateTime registrationDate
) {
    // 생성 시 유효성 검증
    public Payment {
        validateRequired(memberId, "회원 ID");
        validateRequired(paymentMethod, "결제수단");
        validateRequired(paymentInfo, "결제정보");

        // PaymentMethod와 PaymentInfo 타입 일치 검증
        validatePaymentInfoMatchesMethod(paymentMethod, paymentInfo);
    }

    // 신규 결제정보 생성 팩토리 메서드
    public static Payment create(String memberId,
                                  PaymentMethod paymentMethod,
                                  PaymentInfo paymentInfo) {
        return new Payment(
                null,
                memberId,
                paymentMethod,
                paymentInfo,
                LocalDateTime.now()
        );
    }

    // 결제정보 업데이트 (결제수단 변경)
    public Payment updatePaymentInfo(PaymentMethod paymentMethod,
                                      PaymentInfo paymentInfo) {
        return new Payment(
                this.id,
                this.memberId,
                paymentMethod,
                paymentInfo,
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
     * PaymentMethod와 PaymentInfo 타입이 일치하는지 검증
     * 헥사고날 아키텍처의 도메인 불변성 보장
     */
    private static void validatePaymentInfoMatchesMethod(PaymentMethod method, PaymentInfo info) {
        boolean isValid = switch (method) {
            case CMS, REALTIME_CMS -> info instanceof CmsPaymentInfo;
            case CARD -> info instanceof CardPaymentInfo;
            case MOBILE -> info instanceof MobilePaymentInfo;
            case PAYER -> info instanceof PayerPaymentInfo;
            case VIRTUAL_ACCOUNT -> info instanceof VirtualAccountInfo;
        };

        if (!isValid) {
            throw new IllegalArgumentException(
                    String.format("결제수단(%s)과 결제정보 타입(%s)이 일치하지 않습니다",
                            method.getDescription(),
                            info.getClass().getSimpleName())
            );
        }
    }

    // 비즈니스 로직 메서드
    public boolean isCmsPayment() {
        return paymentMethod == PaymentMethod.CMS || paymentMethod == PaymentMethod.REALTIME_CMS;
    }

    public boolean isCardPayment() {
        return paymentMethod == PaymentMethod.CARD;
    }
}