package com.example.dddbackendjy.member.presentation.model;

import java.time.LocalDateTime;

public record MemberDto(
        String id,
        String status,
        String name,
        String memberNumber,
        String email,
        AddressDto address,
        String mobileNumber,
        String landlineNumber,
        LocalDateTime registrationDate,
        boolean smsSendingAllowed,
        String memo
) {
}