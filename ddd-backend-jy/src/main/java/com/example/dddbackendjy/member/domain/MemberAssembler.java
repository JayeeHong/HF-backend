package com.example.dddbackendjy.member.domain;

import com.example.dddbackendjy.common.DomainService;
import com.example.dddbackendjy.member.domain.api.AssembleMember;
import com.example.dddbackendjy.member.infrastructure.db.MemberRepository;

@DomainService
public class MemberAssembler implements AssembleMember {

    private final MemberRepository memberRepository;

    public MemberAssembler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member getMember(String id) {

        var member = memberRepository.findById(id);

        return new Member(
                member.id(),
                member.status(),
                member.name(),
                member.memberNumber(),
                member.email(),
                member.address(),
                member.mobileNumber(),
                member.landlineNumber(),
                member.registrationDate(),
                member.smsSendingAllowed(),
                member.memo()
        );
    }
}
