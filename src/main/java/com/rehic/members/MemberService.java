package com.rehic.members;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MemberService {
    MemberDtoSummary addMember(MemberDto memberDto);

    MemberDto getMember(String id);

    Page<MemberDtoSummary> getAllMember(Pageable pageable);

    void deleteMember(String id);

    MemberDto updateMember(String id, MemberDto dto);

    Page<MemberDto> searchByName(String name, Pageable pageable);
}
