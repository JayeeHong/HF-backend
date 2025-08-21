package com.example.dddbackendjd.member.domain.api;

import com.example.dddbackend.member.domain.Member;
import com.example.dddbackend.member.domain.api.dto.MemberCommand;

public interface RegisterMemberUseCase {

    Member register(MemberCommand.Register command);
}
