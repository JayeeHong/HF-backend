package com.example.dddbackendjy.member.presentation.model;

public record AddressDto(
        String zipCode,
        String address,
        String detailedAddress
) {
}