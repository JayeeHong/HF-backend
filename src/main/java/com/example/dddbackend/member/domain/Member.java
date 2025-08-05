package com.example.dddbackend.member.domain;

import lombok.Builder;

@Builder(toBuilder = true)
public record Member(
        MemberId id,
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
}
