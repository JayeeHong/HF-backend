package com.example.dddbackend.member.presentation.controller;

import com.example.dddbackend.member.application.CreateMemberUseCase;
import com.example.dddbackend.member.application.DeleteMemberUseCase;
import com.example.dddbackend.member.application.GetMemberUseCase;
import com.example.dddbackend.member.application.GetMembersUseCase;
import com.example.dddbackend.member.application.UpdateMemberUseCase;
import com.example.dddbackend.member.presentation.model.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final GetMemberUseCase getMemberUseCase;
    private final GetMembersUseCase getMembersUseCase;
    private final CreateMemberUseCase createMemberUseCase;
    private final UpdateMemberUseCase updateMemberUseCase;
    private final DeleteMemberUseCase deleteMemberUseCase;

    public MemberController(GetMemberUseCase getMemberUseCase,
                           GetMembersUseCase getMembersUseCase,
                           CreateMemberUseCase createMemberUseCase,
                           UpdateMemberUseCase updateMemberUseCase,
                           DeleteMemberUseCase deleteMemberUseCase) {
        this.getMemberUseCase = getMemberUseCase;
        this.getMembersUseCase = getMembersUseCase;
        this.createMemberUseCase = createMemberUseCase;
        this.updateMemberUseCase = updateMemberUseCase;
        this.deleteMemberUseCase = deleteMemberUseCase;
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getMembers() {
        List<MemberDto> members = getMembersUseCase.execute();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable String id) {
        MemberDto member = getMemberUseCase.execute(id);
        return ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody CreateMemberUseCase.CreateMemberRequest request) {
        MemberDto member = createMemberUseCase.execute(request);
        return ResponseEntity.created(URI.create("/api/members/" + member.id())).body(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable String id,
                                                 @RequestBody UpdateMemberUseCase.UpdateMemberRequest request) {
        MemberDto member = updateMemberUseCase.execute(id, request);
        return ResponseEntity.ok(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
        deleteMemberUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}