package com.example.dddbackendjy.domain.document.model;

public record NoneDocumentInfo() implements DocumentInfo {

    // 싱글톤 인스턴스
    public static final NoneDocumentInfo INSTANCE = new NoneDocumentInfo();
}