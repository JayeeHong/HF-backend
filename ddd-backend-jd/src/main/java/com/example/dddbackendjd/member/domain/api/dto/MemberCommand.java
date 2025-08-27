package com.example.dddbackendjd.member.domain.api.dto;

import com.example.dddbackendjd.member.domain.Member;
import com.example.dddbackendjd.member.domain.MemberId;

public record MemberCommand() {

    public record Register(
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
        public Member toDomain() {
            return new Member(
                    null,
                    status,
                    name,
                    memberNumber,
                    email,
                    zipCode,
                    address,
                    detailedAddress,
                    mobileNumber,
                    landlineNumber,
                    registrationDate,
                    isSmsSendingAllowed,
                    memo
            );
        }
    }

    public record Update(
            String id,
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
        public MemberId toId() {
            return new MemberId(id);
        }
    }

    public record Delete(
            String memberId
    ) {
        public MemberId toId() {
            return new MemberId(memberId);
        }
    }

}


