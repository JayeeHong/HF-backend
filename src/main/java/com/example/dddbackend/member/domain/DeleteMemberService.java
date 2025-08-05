package com.example.dddbackend.member.domain;

import com.example.dddbackend.common.annotation.DomainService;
import com.example.dddbackend.member.domain.api.DeleteMemberUseCase;
import com.example.dddbackend.member.domain.api.dto.MemberCommand;
import com.example.dddbackend.member.domain.spi.MemberRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class DeleteMemberService implements DeleteMemberUseCase {

    private final MemberRepository memberRepository;

    @Override
    public void delete(MemberCommand.Delete command) {
        Member member = memberRepository.findById(command.toId());
        memberRepository.delete(member);
    }
}
