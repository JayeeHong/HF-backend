package com.example.dddbackend.member.infrastructure.db;

import com.example.dddbackend.member.domain.Member;
import com.example.dddbackend.member.infrastructure.db.model.MemberMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberRepository implements com.example.dddbackend.member.domain.spi.MemberRepository {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberMapper memberMapper;

    public MemberRepository(MemberJpaRepository memberJpaRepository, MemberMapper memberMapper) {
        this.memberJpaRepository = memberJpaRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public Member findById(String id) {
        return memberJpaRepository.findById(id)
                .map(memberMapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. id: " + id));
    }
}
