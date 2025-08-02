package com.example.dddbackend.member.infrastructure.db;

import com.example.dddbackend.member.domain.Member;
import com.example.dddbackend.member.infrastructure.db.model.MemberMapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public Member save(Member member) {
        var entity = memberMapper.toEntity(member);
        var savedEntity = memberJpaRepository.save(entity);
        return memberMapper.toDomain(savedEntity);
    }

    @Override
    public List<Member> findAll() {
        return memberJpaRepository.findAll()
                .stream()
                .map(memberMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(String id) {
        if (!memberJpaRepository.existsById(id)) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다. id: " + id);
        }
        memberJpaRepository.deleteById(id);
    }
}
