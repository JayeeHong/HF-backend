package com.example.dddbackendjy.domain.payment.model;

public record CardPaymentInfo(
        String cardNumber,
        String expiryDate,
        String cardHolderName,
        IdentificationType identificationType,
        String identificationNumber
) implements PaymentInfo {
    public CardPaymentInfo {
        validateRequired(cardNumber, "카드번호");
        validateRequired(expiryDate, "유효기간");
        validateRequired(cardHolderName, "명의자");
        validateRequired(identificationType, "식별 타입");
        validateRequired(identificationNumber, "식별 번호");

        // 카드번호 검증 (숫자와 하이픈만, 13~19자리)
        String cleanedCardNumber = cardNumber.replaceAll("[^0-9]", "");
        if (cleanedCardNumber.length() < 13 || cleanedCardNumber.length() > 19) {
            throw new IllegalArgumentException("카드번호는 13~19자리여야 합니다: " + cardNumber);
        }

        // 유효기간 검증 (MMYY 또는 MM/YY)
        String cleanedExpiry = expiryDate.replaceAll("[^0-9]", "");
        if (cleanedExpiry.length() != 4) {
            throw new IllegalArgumentException("유효기간은 MMYY 형식이어야 합니다: " + expiryDate);
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