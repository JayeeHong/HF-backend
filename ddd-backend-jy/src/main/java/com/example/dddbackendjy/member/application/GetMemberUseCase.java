package com.example.dddbackendjy.member.application;

import com.example.dddbackendjy.common.UseCase;
import com.example.dddbackendjy.member.domain.api.AssembleMember;
import com.example.dddbackendjy.member.presentation.model.AddressDto;
import com.example.dddbackendjy.member.presentation.model.MemberDto;

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