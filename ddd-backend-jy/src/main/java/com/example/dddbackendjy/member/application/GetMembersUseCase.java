package com.example.dddbackendjy.member.application;

import com.example.dddbackendjy.common.UseCase;
import com.example.dddbackendjy.member.infrastructure.db.MemberRepository;
import com.example.dddbackendjy.member.presentation.model.AddressDto;
import com.example.dddbackendjy.member.presentation.model.MemberDto;

import java.util.List;

@UseCase
public class GetMembersUseCase {

    private final MemberRepository memberRepository;

    public GetMembersUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberDto> execute() {
        return memberRepository.findAll()
                .stream()
                .map(member -> new MemberDto(
                        member.id(),
                        member.status().name(),
                        member.name(),
                        member.memberNumber(),
                        member.email(),
                        new AddressDto(
                                member.address().zipCode(),
                                member.address().address(),
                                member.address().detailedAddress()
                        ),
                        member.mobileNumber(),
                        member.landlineNumber(),
                        member.registrationDate(),
                        member.smsSendingAllowed(),
                        member.memo()
                ))
                .toList();
    }
}