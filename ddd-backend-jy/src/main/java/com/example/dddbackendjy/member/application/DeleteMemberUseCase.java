package com.example.dddbackendjy.member.application;

import com.example.dddbackendjy.common.UseCase;
import com.example.dddbackendjy.member.infrastructure.db.MemberRepository;

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