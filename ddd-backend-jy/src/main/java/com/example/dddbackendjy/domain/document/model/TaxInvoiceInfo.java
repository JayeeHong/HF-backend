package com.example.dddbackendjy.domain.document.model;

public record TaxInvoiceInfo(
        String memberType,
        String taxType,
        String registrationNumber,
        String itemName,
        String issueType,
        String issueMethod
) implements DocumentInfo {
}