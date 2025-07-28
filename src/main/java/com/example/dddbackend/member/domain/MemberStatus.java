package com.example.dddbackend.member.domain;

public enum MemberStatus {
    ACTIVE("이용"),
    SUSPENDED("정지"),
    STOP("중지"),
    DELETED("삭제");

    private final String description;

    MemberStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean canLogin() {
        return this == ACTIVE;
    }
}