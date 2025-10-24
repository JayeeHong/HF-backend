package com.example.dddbackendjy.config;

import com.example.dddbackendjy.application.member.MemberRegistrationService;
import com.example.dddbackendjy.domain.document.DocumentManager;
import com.example.dddbackendjy.domain.document.api.ManageDocuments;
import com.example.dddbackendjy.domain.document.spi.DocumentRepository;
import com.example.dddbackendjy.domain.member.MemberManager;
import com.example.dddbackendjy.domain.member.api.ManageMembers;
import com.example.dddbackendjy.domain.member.spi.MemberRepository;
import com.example.dddbackendjy.domain.payment.PaymentManager;
import com.example.dddbackendjy.domain.payment.api.ManagePayments;
import com.example.dddbackendjy.domain.payment.spi.PaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 도메인 계층 Spring 설정
 *
 * 헥사고날 아키텍처에서:
 * - 도메인 서비스들을 Bean으로 등록
 * - Application Service를 Bean으로 등록
 * - 의존성 주입 설정
 */
@Configuration
public class DomainConfiguration {

    /**
     * 회원 도메인 서비스
     */
    @Bean
    public ManageMembers manageMembers(MemberRepository memberRepository) {
        return new MemberManager(memberRepository);
    }

    /**
     * 결제 도메인 서비스
     */
    @Bean
    public ManagePayments managePayments(PaymentRepository paymentRepository) {
        return new PaymentManager(paymentRepository);
    }

    /**
     * 증빙 도메인 서비스
     */
    @Bean
    public ManageDocuments manageDocuments(DocumentRepository documentRepository) {
        return new DocumentManager(documentRepository);
    }

    /**
     * 회원 등록 Application Service
     */
    @Bean
    public MemberRegistrationService memberRegistrationService(
            ManageMembers manageMembers,
            ManagePayments managePayments,
            ManageDocuments manageDocuments
    ) {
        return new MemberRegistrationService(manageMembers, managePayments, manageDocuments);
    }
}