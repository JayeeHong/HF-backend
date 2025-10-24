package com.example.dddbackendjy.domain.payment.model;

/**
 * 결제 정보를 나타내는 마커 인터페이스
 * sealed interface로 정의하여 타입 안전성을 보장
 */
public sealed interface PaymentInfo
        permits CmsPaymentInfo,
                CardPaymentInfo,
                MobilePaymentInfo,
                PayerPaymentInfo,
                VirtualAccountInfo {
}