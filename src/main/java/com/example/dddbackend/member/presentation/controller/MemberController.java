package com.example.dddbackend.member.presentation.controller;

import com.example.dddbackend.member.application.GetMemberUseCase;
import com.example.dddbackend.member.presentation.model.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final GetMemberUseCase getMemberUseCase;

    public MemberController(GetMemberUseCase getMemberUseCase) {
        this.getMemberUseCase = getMemberUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable String id) {
        MemberDto member = getMemberUseCase.execute(id);
        return ResponseEntity.ok(member);
    }
}