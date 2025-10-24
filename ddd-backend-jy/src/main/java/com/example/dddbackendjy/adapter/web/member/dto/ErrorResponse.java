package com.example.dddbackendjy.adapter.web.member.dto;

import java.time.LocalDateTime;

/**
 * 에러 응답 DTO
 */
public record ErrorResponse(
        String message,
        LocalDateTime timestamp
) {
    public static ErrorResponse of(String message) {
        return new ErrorResponse(message, LocalDateTime.now());
    }
}