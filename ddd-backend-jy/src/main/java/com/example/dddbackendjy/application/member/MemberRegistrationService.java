package com.example.dddbackendjy.application.member;

import com.example.dddbackendjy.application.member.command.RegisterMemberCommand;
import com.example.dddbackendjy.domain.document.api.ManageDocuments;
import com.example.dddbackendjy.domain.document.model.Document;
import com.example.dddbackendjy.domain.member.api.ManageMembers;
import com.example.dddbackendjy.domain.member.model.Member;
import com.example.dddbackendjy.domain.payment.api.ManagePayments;
import com.example.dddbackendjy.domain.payment.model.Payment;

import java.util.UUID;

/**
 * 회원 등록 Application Service
 * 여러 도메인 서비스를 조율하여 회원 등록 Use Case를 구현
 *
 * 헥사고날 아키텍처에서:
 * - 도메인 서비스들을 조율
 * - 트랜잭션 경계 관리 (나중에 @Transactional 추가 예정)
 * - Use Case 구현
 */
public class MemberRegistrationService {

    private final ManageMembers manageMembers;
    private final ManagePayments managePayments;
    private final ManageDocuments manageDocuments;

    public MemberRegistrationService(ManageMembers manageMembers,
                                      ManagePayments managePayments,
                                      ManageDocuments manageDocuments) {
        this.manageMembers = manageMembers;
        this.managePayments = managePayments;
        this.manageDocuments = manageDocuments;
    }

    /**
     * 회원 등록 (기본정보 + 결제정보 + 증빙정보)
     *
     * @param command 회원 등록 커맨드
     * @return 생성된 회원 ID
     */
    public String registerMember(RegisterMemberCommand command) {
        // 1. 회원 ID 생성 (UUID)
        String memberId = UUID.randomUUID().toString();

        // 2. 회원 기본정보 생성 및 등록
        Member member = Member.create(
                command.name(),
                command.memberNumber(),
                command.email(),
                command.address(),
                command.mobileNumber(),
                command.landlineNumber(),
                command.smsSendingAllowed(),
                command.memo()
        );

        // Member에 ID를 설정하기 위해 새로운 인스턴스 생성
        Member memberWithId = new Member(
                memberId,
                member.status(),
                member.name(),
                member.memberNumber(),
                member.email(),
                member.address(),
                member.mobileNumber(),
                member.landlineNumber(),
                member.registrationDate(),
                member.smsSendingAllowed(),
                member.memo()
        );

        manageMembers.addMember(memberWithId);

        // 3. 결제정보 생성 및 등록
        Payment payment = Payment.create(
                memberId,
                command.paymentMethod(),
                command.paymentInfo()
        );
        managePayments.registerPayment(payment);

        // 4. 증빙정보 생성 및 등록
        Document document = Document.create(
                memberId,
                command.documentType(),
                command.documentInfo()
        );
        manageDocuments.registerDocument(document);

        return memberId;
    }
}