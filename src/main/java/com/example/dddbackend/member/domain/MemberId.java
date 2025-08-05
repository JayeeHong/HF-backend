package com.example.dddbackend.member.domain;

import org.springframework.util.Assert;

public record MemberId(
        String id
) {

    public MemberId {
        Assert.notNull(id, "id must not be null");
    }
}
