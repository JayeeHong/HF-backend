package com.example.dddbackendjd.member.domain;

import com.example.dddbackend.common.annotation.DomainService;
import com.example.dddbackend.member.domain.api.RegisterMemberUseCase;
import com.example.dddbackend.member.domain.api.UpdateMemberUseCase;
import com.example.dddbackend.member.domain.api.dto.MemberCommand;
import com.example.dddbackend.member.domain.spi.MemberRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UpdateMemberService implements UpdateMemberUseCase {

    private final MemberRepository memberRepository;

    @Override
    public Member update(MemberCommand.Update command) {
        Member member = memberRepository.findById(command.toId());
        if (member == null) {
            throw new IllegalArgumentException("Member not found");
        }

        Member updatedMember = member.toBuilder()
                .status(command.status())
                .name(command.name())
                .memberNumber(command.memberNumber())
                .email(command.email())
                .zipCode(command.zipCode())
                .address(command.address())
                .detailedAddress(command.detailedAddress())
                .mobileNumber(command.mobileNumber())
                .landlineNumber(command.landlineNumber())
                .registrationDate(command.registrationDate())
                .isSmsSendingAllowed(command.isSmsSendingAllowed())
                .memo(command.memo())
                .build();

        return memberRepository.save(updatedMember);
    }
}
