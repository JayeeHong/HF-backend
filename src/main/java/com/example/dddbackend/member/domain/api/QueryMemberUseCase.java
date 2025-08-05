package com.example.dddbackend.member.domain.api;

import com.example.dddbackend.member.domain.Member;

public interface QueryMemberUseCase {

    Member findById(String id);
}
