package com.example.dddbackendjy.member.domain;

public record Address(String zipCode,
                      String address,
                      String detailedAddress) {
    
    public Address {
        if (zipCode == null || zipCode.trim().isEmpty()) {
            throw new IllegalArgumentException("우편번호는 필수입니다");
        }

        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("주소는 필수입니다");
        }

        // 우편번호 형식 검증 (5자리 숫자)
        if (!zipCode.matches("\\d{5}")) {
            throw new IllegalArgumentException("우편번호 형식이 올바르지 않습니다");
        }
    }

    public String getFullAddress() {
        return String.format("(%s) %s %s", zipCode, address, detailedAddress != null ? detailedAddress : "").trim();
    }
}