package com.example.dddbackend.member.application;

import com.example.dddbackend.common.annotation.UseCase;
import com.example.dddbackend.member.domain.api.AssembleMember;
import com.example.dddbackend.member.presentation.model.AddressDto;
import com.example.dddbackend.member.presentation.model.MemberDto;

@UseCase
public class GetMemberUseCase {

    private final AssembleMember assembleMember;

    public GetMemberUseCase(AssembleMember assembleMember) {
        this.assembleMember = assembleMember;
    }

    public MemberDto execute(String id) {
        var member = assembleMember.getMember(id);
        return new MemberDto(
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
        );
    }
}