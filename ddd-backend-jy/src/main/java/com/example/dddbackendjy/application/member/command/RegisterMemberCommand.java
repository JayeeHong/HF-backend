package com.example.dddbackendjy.application.member.command;

import com.example.dddbackendjy.domain.document.model.DocumentInfo;
import com.example.dddbackendjy.domain.document.model.DocumentType;
import com.example.dddbackendjy.domain.member.model.Address;
import com.example.dddbackendjy.domain.payment.model.PaymentInfo;
import com.example.dddbackendjy.domain.payment.model.PaymentMethod;

/**
 * 회원 등록 커맨드
 * 회원 기본정보, 결제정보, 증빙정보를 함께 등록하기 위한 DTO
 */
public record RegisterMemberCommand(
        // 회원 기본정보
        String name,
        String memberNumber,
        String email,
        Address address,
        String mobileNumber,
        String landlineNumber,
        boolean smsSendingAllowed,
        String memo,

        // 결제정보
        PaymentMethod paymentMethod,
        PaymentInfo paymentInfo,

        // 증빙정보
        DocumentType documentType,
        DocumentInfo documentInfo
) {
    public RegisterMemberCommand {
        // 필수 필드 검증
        validateRequired(name, "이름");
        validateRequired(memberNumber, "회원번호");
        validateRequired(email, "이메일");
        validateRequired(mobileNumber, "휴대폰번호");
        validateRequired(paymentMethod, "결제수단");
        validateRequired(paymentInfo, "결제정보");
        validateRequired(documentType, "증빙 타입");
        validateRequired(documentInfo, "증빙 정보");
    }

    private static void validateRequired(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다");
        }
        if (value instanceof String str && str.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다");
        }
    }
}