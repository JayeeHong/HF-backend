package com.example.dddbackendjd.member.domain;

import com.example.dddbackend.common.annotation.DomainService;
import com.example.dddbackend.member.domain.api.QueryMemberUseCase;
import com.example.dddbackend.member.domain.api.RegisterMemberUseCase;
import com.example.dddbackend.member.domain.api.dto.MemberCommand;
import com.example.dddbackend.member.domain.spi.MemberRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class QueryMemberService implements QueryMemberUseCase {

    private final MemberRepository memberRepository;

    @Override
    public Member findById(String id) {
        return memberRepository.findById(new MemberId(id));
    }
}
