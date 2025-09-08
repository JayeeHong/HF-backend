package com.example.dddbackendjy.member.infrastructure.db.model;

import com.example.dddbackendjy.member.domain.Address;
import com.example.dddbackendjy.member.domain.Member;
import com.example.dddbackendjy.member.domain.MemberStatus;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberEntity toEntity(Member member) {
        Address address = member.address();
        return new MemberEntity(
                member.id(),
                mapToEntityStatus(member.status()),
                member.name(),
                member.memberNumber(),
                member.email(),
                address.zipCode(),
                address.address(),
                address.detailedAddress(),
                member.mobileNumber(),
                member.landlineNumber(),
                member.registrationDate(),
                member.smsSendingAllowed(),
                member.memo()
        );
    }

    public Member toDomain(MemberEntity entity) {
        Address address = new Address(
                entity.getZipCode(),
                entity.getAddress(),
                entity.getDetailedAddress()
        );
        
        return new Member(
                entity.getId(),
                mapToDomainStatus(entity.getStatus()),
                entity.getName(),
                entity.getMemberNumber(),
                entity.getEmail(),
                address,
                entity.getMobileNumber(),
                entity.getLandlineNumber(),
                entity.getRegistrationDate(),
                entity.isSmsSendingAllowed(),
                entity.getMemo()
        );
    }

    private MemberEntity.MemberStatusType mapToEntityStatus(MemberStatus status) {
        return switch (status) {
            case ACTIVE -> MemberEntity.MemberStatusType.ACTIVE;
            case SUSPENDED -> MemberEntity.MemberStatusType.SUSPENDED;
            case STOP -> MemberEntity.MemberStatusType.STOP;
            case DELETED -> MemberEntity.MemberStatusType.DELETED;
        };
    }

    private MemberStatus mapToDomainStatus(MemberEntity.MemberStatusType statusType) {
        return switch (statusType) {
            case ACTIVE -> MemberStatus.ACTIVE;
            case SUSPENDED -> MemberStatus.SUSPENDED;
            case STOP -> MemberStatus.STOP;
            case DELETED -> MemberStatus.DELETED;
        };
    }
}