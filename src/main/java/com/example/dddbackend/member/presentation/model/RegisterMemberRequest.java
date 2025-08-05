package com.example.dddbackend.member.presentation.model;

import com.example.dddbackend.member.domain.api.dto.MemberCommand;

public record RegisterMemberRequest(
        String status,
        String name,
        String memberNumber,
        String email,
        String zipCode,
        String address,
        String detailedAddress,
        String mobileNumber,
        String landlineNumber,
        String registrationDate,
        boolean isSmsSendingAllowed,
        String memo
) {

    public MemberCommand.Register toCommand() {
        return new MemberCommand.Register(
                status, name, memberNumber, email, zipCode, address, detailedAddress,  mobileNumber, landlineNumber, registrationDate, isSmsSendingAllowed, memo
        );
    }
}
