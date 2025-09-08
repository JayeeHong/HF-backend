package com.example.dddbackendjy.member.infrastructure.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MemberStatusType status;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "member_number", unique = true, nullable = false)
    private String memberNumber;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "zip_code", length = 5, nullable = false)
    private String zipCode;
    
    @Column(name = "address", nullable = false)
    private String address;
    
    @Column(name = "detailed_address")
    private String detailedAddress;
    
    @Column(name = "mobile_number")
    private String mobileNumber;
    
    @Column(name = "landline_number")
    private String landlineNumber;
    
    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;
    
    @Column(name = "sms_sending_allowed", nullable = false)
    private boolean smsSendingAllowed;
    
    @Column(name = "memo", length = 1000)
    private String memo;

    public enum MemberStatusType {
        ACTIVE, SUSPENDED, STOP, DELETED
    }
}
