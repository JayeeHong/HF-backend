package com.example.dddbackendjy.adapter.persistence.payment;

import com.example.dddbackendjy.domain.payment.model.Payment;
import com.example.dddbackendjy.domain.payment.spi.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 결제정보 저장소 InMemory 구현체 (Secondary Adapter)
 */
@Repository
public class InMemoryPaymentRepository implements PaymentRepository {

    private final Map<String, Payment> store = new ConcurrentHashMap<>();

    @Override
    public void save(Payment payment) {
        store.put(payment.id(), payment);
    }

    @Override
    public Optional<Payment> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Payment> findByMemberId(String memberId) {
        return store.values().stream()
                .filter(payment -> payment.memberId().equals(memberId))
                .findFirst();
    }

    @Override
    public boolean existsByMemberId(String memberId) {
        return store.values().stream()
                .anyMatch(payment -> payment.memberId().equals(memberId));
    }
}