package com.example.dddbackendjd.member.domain.spi;

import com.example.dddbackend.member.domain.Member;
import com.example.dddbackend.member.domain.MemberId;

public interface MemberRepository {

    Member findById(MemberId id);

    Member save(Member member);

    Member update(Member member);

    void delete(Member member);

}
