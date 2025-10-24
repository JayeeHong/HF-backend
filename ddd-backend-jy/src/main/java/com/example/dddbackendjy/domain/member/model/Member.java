package com.example.dddbackendjy.domain.member.model;

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

    // 생성 시 유효성 검증
    public Member {
        validateRequired(status, "회원 상태");
        validateRequired(name, "이름");
        validateRequired(memberNumber, "회원번호");
        validateRequired(email, "이메일");
        validateRequired(mobileNumber, "휴대폰번호");

        validateEmail(email);
        validateMobileNumber(mobileNumber);
    }

    // 신규 회원 생성 팩토리 메서드
    public static Member create(String name,
                                String memberNumber,
                                String email,
                                Address address,
                                String mobileNumber,
                                String landlineNumber,
                                boolean smsSendingAllowed,
                                String memo) {
        return new Member(
                null,
                MemberStatus.ACTIVE,
                name,
                memberNumber,
                email,
                address,
                mobileNumber,
                landlineNumber,
                LocalDateTime.now(),
                smsSendingAllowed,
                memo
        );
    }

    // 유효성 검증 메서드들
    private static void validateRequired(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다");
        }
        if (value instanceof String str && str.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다");
        }
    }

    private static void validateEmail(String email) {
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다: " + email);
        }

        String[] parts = email.split("@");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다 (id@domain.com): " + email);
        }
    }

    private static void validateMobileNumber(String mobileNumber) {
        String cleaned = mobileNumber.replaceAll("[^0-9]", "");
        if (cleaned.length() != 11) {
            throw new IllegalArgumentException("휴대폰번호는 11자리 숫자여야 합니다: " + mobileNumber);
        }
    }
}
