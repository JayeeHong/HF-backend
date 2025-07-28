package com.example.dddbackend.member.infrastructure.db;

import com.example.dddbackend.member.infrastructure.db.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, String> {
}
