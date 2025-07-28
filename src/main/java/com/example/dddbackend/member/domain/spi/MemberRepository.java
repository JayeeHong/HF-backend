package com.example.dddbackend.member.domain.spi;

import com.example.dddbackend.member.domain.Member;

public interface MemberRepository {

    Member findById(String id);

}
