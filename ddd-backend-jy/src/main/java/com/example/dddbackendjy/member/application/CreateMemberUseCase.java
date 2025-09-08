package com.example.dddbackendjy.member.application;

import com.example.dddbackendjy.common.UseCase;
import com.example.dddbackendjy.member.domain.Address;
import com.example.dddbackendjy.member.domain.Member;
import com.example.dddbackendjy.member.domain.MemberStatus;
import com.example.dddbackendjy.member.domain.spi.MemberRepository;
import com.example.dddbackendjy.member.presentation.model.AddressDto;
import com.example.dddbackendjy.member.presentation.model.MemberDto;

import java.time.LocalDateTime;
import java.util.UUID;

@UseCase
public class CreateMemberUseCase {

    private final MemberRepository memberRepository;

    public CreateMemberUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDto execute(CreateMemberRequest request) {
        String id = UUID.randomUUID().toString();
        String memberNumber = generateMemberNumber();
        
        Address address = new Address(
                request.address().zipCode(),
                request.address().address(),
                request.address().detailedAddress()
        );

        Member member = new Member(
                id,
                MemberStatus.ACTIVE,
                request.name(),
                memberNumber,
                request.email(),
                address,
                request.mobileNumber(),
                request.landlineNumber(),
                LocalDateTime.now(),
                request.smsSendingAllowed(),
                request.memo()
        );

        Member savedMember = memberRepository.save(member);

        return new MemberDto(
                savedMember.id(),
                savedMember.status().name(),
                savedMember.name(),
                savedMember.memberNumber(),
                savedMember.email(),
                new AddressDto(
                        savedMember.address().zipCode(),
                        savedMember.address().address(),
                        savedMember.address().detailedAddress()
                ),
                savedMember.mobileNumber(),
                savedMember.landlineNumber(),
                savedMember.registrationDate(),
                savedMember.smsSendingAllowed(),
                savedMember.memo()
        );
    }

    private String generateMemberNumber() {
        return "MEM" + System.currentTimeMillis();
    }

    public record CreateMemberRequest(
            String name,
            String email,
            AddressDto address,
            String mobileNumber,
            String landlineNumber,
            boolean smsSendingAllowed,
            String memo
    ) {}
}