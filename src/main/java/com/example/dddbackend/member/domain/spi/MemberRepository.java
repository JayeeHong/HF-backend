package com.example.dddbackend.member.domain.spi;

import com.example.dddbackend.member.domain.Member;

import java.util.List;

public interface MemberRepository {

    Member findById(String id);
    
    List<Member> findAll();
    
    Member save(Member member);
    
    void deleteById(String id);

}
