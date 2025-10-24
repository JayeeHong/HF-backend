package com.example.dddbackendjy.domain.payment.spi;

import com.example.dddbackendjy.domain.payment.model.Payment;

import java.util.Optional;

/**
 * 결제정보 저장소 아웃바운드 포트
 * 도메인에서 외부(저장소)로 나가는 인터페이스
 */
public interface PaymentRepository {

    void save(Payment payment);

    Optional<Payment> findById(String id);

    Optional<Payment> findByMemberId(String memberId);

    boolean existsByMemberId(String memberId);
}