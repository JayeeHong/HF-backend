package com.example.dddbackendjy.domain.payment;

import com.example.dddbackendjy.domain.common.DomainService;
import com.example.dddbackendjy.domain.payment.api.ManagePayments;
import com.example.dddbackendjy.domain.payment.model.Payment;
import com.example.dddbackendjy.domain.payment.spi.PaymentRepository;

import java.util.Optional;

@DomainService
public class PaymentManager implements ManagePayments {

    private final PaymentRepository paymentRepository;

    public PaymentManager(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void registerPayment(Payment payment) {
        // 비즈니스 규칙: 회원당 하나의 결제정보만 등록 가능
        if (paymentRepository.existsByMemberId(payment.memberId())) {
            throw new IllegalArgumentException(
                    "이미 등록된 결제정보가 있습니다. 회원 ID: " + payment.memberId()
            );
        }

        // 결제정보 저장
        paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> findPaymentByMemberId(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("회원 ID는 필수입니다");
        }

        return paymentRepository.findByMemberId(memberId);
    }

    @Override
    public void updatePayment(Payment payment) {
        // 기존 결제정보 존재 여부 확인
        Payment existingPayment = paymentRepository.findByMemberId(payment.memberId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "등록된 결제정보가 없습니다. 회원 ID: " + payment.memberId()
                ));

        // ID 유지하면서 업데이트
        Payment updatedPayment = new Payment(
                existingPayment.id(),
                payment.memberId(),
                payment.paymentMethod(),
                payment.paymentInfo(),
                existingPayment.registrationDate()
        );

        paymentRepository.save(updatedPayment);
    }
}