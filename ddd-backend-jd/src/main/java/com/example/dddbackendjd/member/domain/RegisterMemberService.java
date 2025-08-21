package com.example.dddbackendjd.member.domain;

import com.example.dddbackend.common.annotation.DomainService;
import com.example.dddbackend.member.domain.api.RegisterMemberUseCase;
import com.example.dddbackend.member.domain.api.dto.MemberCommand;
import com.example.dddbackend.member.domain.spi.MemberRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class RegisterMemberService implements RegisterMemberUseCase {

    private final MemberRepository memberRepository;

    @Override
    public Member register(MemberCommand.Register command) {
        Member newMember = command.toDomain();
        return memberRepository.save(newMember);
    }
}
