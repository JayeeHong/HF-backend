package com.example.dddbackendjy.domain.payment.api;

import com.example.dddbackendjy.domain.payment.model.Payment;

import java.util.Optional;

/**
 * 결제정보 관리 인바운드 포트
 * 외부(컨트롤러)에서 도메인으로 들어오는 인터페이스
 */
public interface ManagePayments {

    // 회원의 결제정보를 등록
    void registerPayment(Payment payment);

    // memberId로 결제정보 조회
    Optional<Payment> findPaymentByMemberId(String memberId);

    // 결제정보 수정
    void updatePayment(Payment payment);
}