package com.example.dddbackendjy.domain.member;

import com.example.dddbackendjy.domain.common.DomainService;
import com.example.dddbackendjy.domain.member.api.ManageMembers;
import com.example.dddbackendjy.domain.member.model.Member;
import com.example.dddbackendjy.domain.member.spi.MemberRepository;

@DomainService
public class MemberManager implements ManageMembers {

    private final MemberRepository memberRepository;

    public MemberManager(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void addMember(Member member) {

        // 회원 번호 중복 검사
        if (memberRepository.existsByMemberNumber(member.memberNumber())) {
            throw new IllegalArgumentException("이미 등록된 회원번호입니다: " + member.memberNumber());
        }

        // 이메일 중복 검사
        if (memberRepository.existsByEmail(member.email())) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다: " + member.email());
        }

        // 회원 저장
        memberRepository.save(member);
    }
}