package com.example.dddbackend.member.presentation.model;

public record AddressDto(
        String zipCode,
        String address,
        String detailedAddress
) {
}