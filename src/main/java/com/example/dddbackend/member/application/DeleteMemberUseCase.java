package com.example.dddbackend.member.application;

import com.example.dddbackend.common.annotation.UseCase;
import com.example.dddbackend.member.domain.spi.MemberRepository;

@UseCase
public class DeleteMemberUseCase {

    private final MemberRepository memberRepository;

    public DeleteMemberUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void execute(String id) {
        memberRepository.deleteById(id);
    }
}