package com.example.dddbackendjy.adapter.persistence.member;

import com.example.dddbackendjy.domain.member.model.Member;
import com.example.dddbackendjy.domain.member.spi.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 회원 저장소 InMemory 구현체 (Secondary Adapter)
 *
 * 헥사고날 아키텍처에서:
 * - Secondary Adapter 역할
 * - MemberRepository 인터페이스 구현
 * - 실제 저장소 기술(InMemory)에 대한 구현
 */
@Repository
public class InMemoryMemberRepository implements MemberRepository {

    private final Map<String, Member> store = new ConcurrentHashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.id(), member);
    }

    @Override
    public boolean existsByMemberNumber(String memberNumber) {
        return store.values().stream()
                .anyMatch(member -> member.memberNumber().equals(memberNumber));
    }

    @Override
    public boolean existsByEmail(String email) {
        return store.values().stream()
                .anyMatch(member -> member.email().equals(email));
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return store.values().stream()
                .filter(member -> member.email().equals(email))
                .findFirst();
    }
}