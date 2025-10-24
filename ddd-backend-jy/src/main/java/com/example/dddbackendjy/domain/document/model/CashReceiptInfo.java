package com.example.dddbackendjy.domain.document.model;

public record CashReceiptInfo(
        String issueMethod,
        String cashReceiptInfo
) implements DocumentInfo {
}