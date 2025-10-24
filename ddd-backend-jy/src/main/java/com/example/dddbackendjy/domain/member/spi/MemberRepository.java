package com.example.dddbackendjy.domain.member.spi;

import com.example.dddbackendjy.domain.member.model.Member;

import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    boolean existsByMemberNumber(String memberNumber);

    boolean existsByEmail(String email);

    Optional<Member> findById(String id);

    Optional<Member> findByEmail(String email);
}
