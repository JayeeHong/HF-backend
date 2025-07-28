package com.example.dddbackend.member.domain;

import java.time.LocalDateTime;

public record Member(String id,
                     MemberStatus status,
                     String name,
                     String memberNumber,
                     String email,
                     Address address,
                     String mobileNumber,
                     String landlineNumber,
                     LocalDateTime registrationDate,
                     boolean smsSendingAllowed,
                     String memo) {
}
