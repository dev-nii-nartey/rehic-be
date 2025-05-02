package com.rehic.members;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/rehic")
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    @PostMapping("/member")
    public MemberDtoSummary createMember(@RequestBody MemberDto memberDto) {
        return memberService.addMember(memberDto);
    }

    @GetMapping("/{memberId}")
    public MemberDto getMember(@PathVariable String memberId) {
        return memberService.getMember(memberId);
    }


    @GetMapping("/members")
    public Page<MemberDtoSummary> getAllMember(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return memberService.getAllMember(PageRequest.of(page, size));
    }


    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
    }

    @PutMapping
    public MemberDto updateMember( @RequestBody MemberDto dto) {
        return memberService.updateMember(dto.getRecordId(), dto);
    }


    @GetMapping("/search")
    public Page<MemberDto> searchMembers(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return memberService.searchByName(name, PageRequest.of(page, size));
    }

}
