package com.example.dddbackendjy.domain.payment.model;

public record CmsPaymentInfo(
        String bankName,
        String accountNumber,
        IdentificationType identificationType,
        String identificationNumber,
        String accountHolderName,
        ConsentType consentType,
        String consentFilePath
) implements PaymentInfo {
    public CmsPaymentInfo {
        validateRequired(bankName, "은행 이름");
        validateRequired(accountNumber, "계좌번호");
        validateRequired(identificationType, "식별 타입");
        validateRequired(identificationNumber, "식별 번호");
        validateRequired(accountHolderName, "예금주 이름");
        validateRequired(consentType, "동의 타입");
        validateRequired(consentFilePath, "동의 파일 경로");

        // 계좌번호 검증 (숫자만)
        if (!accountNumber.matches("\\d+")) {
            throw new IllegalArgumentException("계좌번호는 숫자만 입력 가능합니다: " + accountNumber);
        }

        // 식별번호 검증
        validateIdentificationNumber(identificationType, identificationNumber);
    }

    private static void validateRequired(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다");
        }
        if (value instanceof String str && str.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다");
        }
    }

    private static void validateIdentificationNumber(IdentificationType type, String number) {
        String cleaned = number.replaceAll("[^0-9]", "");

        if (type == IdentificationType.PERSONAL) {
            // 생년월일: 6자리 (YYMMDD)
            if (cleaned.length() != 6) {
                throw new IllegalArgumentException("생년월일은 6자리여야 합니다 (YYMMDD): " + number);
            }
        } else if (type == IdentificationType.BUSINESS) {
            // 사업자등록번호: 10자리
            if (cleaned.length() != 10) {
                throw new IllegalArgumentException("사업자등록번호는 10자리여야 합니다: " + number);
            }
        }
    }
}