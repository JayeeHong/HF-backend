package com.example.dddbackendjy.adapter.web.member.dto;

/**
 * 회원 등록 HTTP 응답 DTO
 */
public record RegisterMemberResponse(
        String memberId,
        String message
) {
    public static RegisterMemberResponse of(String memberId) {
        return new RegisterMemberResponse(
                memberId,
                "회원이 성공적으로 등록되었습니다."
        );
    }
}