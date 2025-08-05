package com.example.dddbackend.member.domain.api;

import com.example.dddbackend.member.domain.Member;
import com.example.dddbackend.member.domain.api.dto.MemberCommand;

public interface UpdateMemberUseCase {

    Member update(MemberCommand.Update command);
}
