package com.example.dddbackendjy.member.application;

import com.example.dddbackendjy.common.UseCase;
import com.example.dddbackendjy.member.domain.Address;
import com.example.dddbackendjy.member.domain.Member;
import com.example.dddbackendjy.member.infrastructure.db.MemberRepository;
import com.example.dddbackendjy.member.presentation.model.AddressDto;
import com.example.dddbackendjy.member.presentation.model.MemberDto;

@UseCase
public class UpdateMemberUseCase {

    private final MemberRepository memberRepository;

    public UpdateMemberUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDto execute(String id, UpdateMemberRequest request) {
        Member existingMember = memberRepository.findById(id);
        
        Address address = new Address(
                request.address().zipCode(),
                request.address().address(),
                request.address().detailedAddress()
        );

        Member updatedMember = new Member(
                existingMember.id(),
                existingMember.status(),
                request.name(),
                existingMember.memberNumber(),
                request.email(),
                address,
                request.mobileNumber(),
                request.landlineNumber(),
                existingMember.registrationDate(),
                request.smsSendingAllowed(),
                request.memo()
        );

        Member savedMember = memberRepository.save(updatedMember);

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

    public record UpdateMemberRequest(
            String name,
            String email,
            AddressDto address,
            String mobileNumber,
            String landlineNumber,
            boolean smsSendingAllowed,
            String memo
    ) {}
}