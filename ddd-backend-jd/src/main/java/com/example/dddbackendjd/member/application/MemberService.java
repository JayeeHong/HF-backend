package com.example.dddbackendjd.member.application;

import com.example.dddbackendjd.member.domain.Member;
import com.example.dddbackendjd.member.domain.api.RegisterMemberUseCase;
import com.example.dddbackendjd.member.presentation.model.RegisterMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final RegisterMemberUseCase registerMemberUseCase;

    public Member register(RegisterMemberRequest request) {
        var command = request.toCommand();

        return registerMemberUseCase.register(command);
    }
}
