package com.example.dddbackendjy.adapter.web.member.dto;

import com.example.dddbackendjy.domain.document.model.DocumentInfo;
import com.example.dddbackendjy.domain.document.model.DocumentType;
import com.example.dddbackendjy.domain.member.model.Address;
import com.example.dddbackendjy.domain.payment.model.PaymentInfo;
import com.example.dddbackendjy.domain.payment.model.PaymentMethod;

/**
 * 회원 등록 HTTP 요청 DTO
 * Primary Adapter에서 사용
 */
public record RegisterMemberRequest(
        // 회원 기본정보
        String name,
        String memberNumber,
        String email,
        AddressDto address,
        String mobileNumber,
        String landlineNumber,
        boolean smsSendingAllowed,
        String memo,

        // 결제정보
        String paymentMethod,
        PaymentInfoDto paymentInfo,

        // 증빙정보
        String documentType,
        DocumentInfoDto documentInfo
) {
    /**
     * 주소 DTO
     */
    public record AddressDto(
            String zipCode,
            String address,
            String detailedAddress
    ) {
        public Address toDomain() {
            return new Address(zipCode, address, detailedAddress);
        }
    }

    /**
     * 결제정보 DTO (다형성 처리를 위한 마커 인터페이스)
     */
    public sealed interface PaymentInfoDto
            permits CmsPaymentInfoDto,
                    CardPaymentInfoDto,
                    MobilePaymentInfoDto,
                    PayerPaymentInfoDto,
                    VirtualAccountInfoDto {
        PaymentInfo toDomain();
    }

    /**
     * 증빙정보 DTO (다형성 처리를 위한 마커 인터페이스)
     */
    public sealed interface DocumentInfoDto
            permits CashReceiptInfoDto,
                    TaxInvoiceInfoDto,
                    NoneDocumentInfoDto {
        DocumentInfo toDomain();
    }

    // 결제정보 DTO 구현체들
    public record CmsPaymentInfoDto(
            String bankName,
            String accountNumber,
            String identificationType,
            String identificationNumber,
            String accountHolderName,
            String consentType,
            String consentFilePath
    ) implements PaymentInfoDto {
        @Override
        public PaymentInfo toDomain() {
            return new com.example.dddbackendjy.domain.payment.model.CmsPaymentInfo(
                    bankName,
                    accountNumber,
                    com.example.dddbackendjy.domain.payment.model.IdentificationType.valueOf(identificationType),
                    identificationNumber,
                    accountHolderName,
                    com.example.dddbackendjy.domain.payment.model.ConsentType.valueOf(consentType),
                    consentFilePath
            );
        }
    }

    public record CardPaymentInfoDto(
            String cardNumber,
            String expiryDate,
            String cardHolderName,
            String identificationType,
            String identificationNumber
    ) implements PaymentInfoDto {
        @Override
        public PaymentInfo toDomain() {
            return new com.example.dddbackendjy.domain.payment.model.CardPaymentInfo(
                    cardNumber,
                    expiryDate,
                    cardHolderName,
                    com.example.dddbackendjy.domain.payment.model.IdentificationType.valueOf(identificationType),
                    identificationNumber
            );
        }
    }

    public record MobilePaymentInfoDto(
            String telecomOperator,
            String residentNumber,
            String holderName
    ) implements PaymentInfoDto {
        @Override
        public PaymentInfo toDomain() {
            return new com.example.dddbackendjy.domain.payment.model.MobilePaymentInfo(
                    com.example.dddbackendjy.domain.payment.model.TelecomOperator.valueOf(telecomOperator),
                    residentNumber,
                    holderName
            );
        }
    }

    public record PayerPaymentInfoDto(
            boolean cardEnabled,
            boolean accountEnabled,
            boolean simplePaymentEnabled
    ) implements PaymentInfoDto {
        @Override
        public PaymentInfo toDomain() {
            return new com.example.dddbackendjy.domain.payment.model.PayerPaymentInfo(
                    cardEnabled,
                    accountEnabled,
                    simplePaymentEnabled
            );
        }
    }

    public record VirtualAccountInfoDto(
            String depositorName
    ) implements PaymentInfoDto {
        @Override
        public PaymentInfo toDomain() {
            return new com.example.dddbackendjy.domain.payment.model.VirtualAccountInfo(
                    depositorName
            );
        }
    }

    // 증빙정보 DTO 구현체들
    public record CashReceiptInfoDto(
            String issueMethod,
            String cashReceiptInfo
    ) implements DocumentInfoDto {
        @Override
        public DocumentInfo toDomain() {
            return new com.example.dddbackendjy.domain.document.model.CashReceiptInfo(
                    issueMethod,
                    cashReceiptInfo
            );
        }
    }

    public record TaxInvoiceInfoDto(
            String memberType,
            String taxType,
            String registrationNumber,
            String itemName,
            String issueType,
            String issueMethod
    ) implements DocumentInfoDto {
        @Override
        public DocumentInfo toDomain() {
            return new com.example.dddbackendjy.domain.document.model.TaxInvoiceInfo(
                    memberType,
                    taxType,
                    registrationNumber,
                    itemName,
                    issueType,
                    issueMethod
            );
        }
    }

    public record NoneDocumentInfoDto() implements DocumentInfoDto {
        @Override
        public DocumentInfo toDomain() {
            return com.example.dddbackendjy.domain.document.model.NoneDocumentInfo.INSTANCE;
        }
    }

    // Request를 Domain으로 변환하는 헬퍼 메서드
    public PaymentMethod getPaymentMethodEnum() {
        return PaymentMethod.valueOf(paymentMethod);
    }

    public DocumentType getDocumentTypeEnum() {
        return DocumentType.valueOf(documentType);
    }
}