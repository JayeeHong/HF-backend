package com.example.dddbackendjy.adapter.web.member;

import com.example.dddbackendjy.adapter.web.member.dto.ErrorResponse;
import com.example.dddbackendjy.adapter.web.member.dto.RegisterMemberRequest;
import com.example.dddbackendjy.adapter.web.member.dto.RegisterMemberResponse;
import com.example.dddbackendjy.application.member.MemberRegistrationService;
import com.example.dddbackendjy.application.member.command.RegisterMemberCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 회원 관리 REST Controller (Primary Adapter)
 *
 * 헥사고날 아키텍처에서:
 * - Primary Adapter 역할
 * - HTTP 요청을 받아서 Application Service로 전달
 * - 도메인 로직은 호출하지 않음 (Application Service에 위임)
 */
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberRegistrationService memberRegistrationService;

    public MemberController(MemberRegistrationService memberRegistrationService) {
        this.memberRegistrationService = memberRegistrationService;
    }

    /**
     * 회원 등록 API
     * POST /api/members
     */
    @PostMapping
    public ResponseEntity<?> registerMember(@RequestBody RegisterMemberRequest request) {
        try {
            // 1. HTTP Request를 Command로 변환
            RegisterMemberCommand command = new RegisterMemberCommand(
                    request.name(),
                    request.memberNumber(),
                    request.email(),
                    request.address().toDomain(),
                    request.mobileNumber(),
                    request.landlineNumber(),
                    request.smsSendingAllowed(),
                    request.memo(),
                    request.getPaymentMethodEnum(),
                    request.paymentInfo().toDomain(),
                    request.getDocumentTypeEnum(),
                    request.documentInfo().toDomain()
            );

            // 2. Application Service 호출
            String memberId = memberRegistrationService.registerMember(command);

            // 3. 응답 생성
            RegisterMemberResponse response = RegisterMemberResponse.of(memberId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            // 4. 비즈니스 예외 처리
            ErrorResponse errorResponse = ErrorResponse.of(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        } catch (Exception e) {
            // 5. 시스템 예외 처리
            ErrorResponse errorResponse = ErrorResponse.of("서버 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}