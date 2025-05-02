package com.rehic.members;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberDtoSummary addMember(MemberDto memberDto) {
        // Check if email already exists
        memberRepository.findByEmailAddress(memberDto.getEmailAddress()).ifPresent(m -> {
            throw new IllegalArgumentException("Email address already exists: " + memberDto.getEmailAddress());
        });

        Member member = new Member.MemberBuilder(memberDto.getFirstName(), memberDto.getLastName(), memberDto.getEmailAddress())
                .dateOfBirth(memberDto.getDateOfBirth())
                .ministriesOfInterest(memberDto.getMinistriesOfInterest())
                .primaryPhone(memberDto.getPrimaryPhone())
                .residingAddress(memberDto.getResidingAddress())
                .maritalStatus(memberDto.getMaritalStatus())
                .attendanceStatus(memberDto.getAttendanceStatus())
                .gender(memberDto.getGender())
                .emergencyContact(memberDto.getEmergencyContact())
                .emergencyContactRelationship(memberDto.getEmergencyContactRelationship())
                .occupation(memberDto.getOccupation())
                .skills(memberDto.getSkills())
                .lastAttendance(memberDto.getLastAttendance())
                .joinDate(memberDto.getJoinDate())
                .baptized(memberDto.getBaptized())
                .build();
        Member save = memberRepository.save(member);
        return new MemberDtoSummary(save);
    }

    @Override
    public MemberDto getMember(String id) { 
        Member member = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Member not found with id: " + id)); // Changed parameter type
        if (member.getIsDeleted()) {
            throw new NoSuchElementException("Member not found with id: " + id); // Treat deleted as not found
        }
        return new MemberDto(member);
    }


    @Override
    public Page<MemberDtoSummary> getAllMember(Pageable pageable) {
         // Fixed implementation
        return memberRepository.findAllByIsDeletedFalse(pageable).map(MemberDtoSummary::new);
    }

    // Implemented deleteMember
    @Override
    public void deleteMember(String id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Member not found with id: " + id));

        // Create a new Member instance with the same ID but isDeleted=true
        Member deletedMember = new Member.MemberBuilder(member.getFirstName(), member.getLastName(), member.getEmailAddress())
                .id(member.getId()) // Preserve the original ID
                .dateOfBirth(member.getDateOfBirth())
                .gender(member.getGender())
                .maritalStatus(member.getMaritalStatus())
                .primaryPhone(member.getPrimaryPhone())
                .residingAddress(member.getResidingAddress())
                .ministriesOfInterest(member.getMinistriesOfInterest())
                .attendanceStatus(member.getAttendanceStatus())
                .emergencyContact(member.getEmergencyContact())
                .emergencyContactRelationship(member.getEmergencyContactRelationship())
                .occupation(member.getOccupation())
                .skills(member.getSkills())
                .lastAttendance(member.getLastAttendance())
                .joinDate(member.getJoinDate())
                .baptized(member.getBaptized())
                .isDeleted(true) // Set isDeleted to true
                .build();

        // Save the updated member; Spring Data MongoDB save() performs an upsert (update if ID exists)
        memberRepository.save(deletedMember);
    }

    // Implemented updateMember
    @Override
    public MemberDto updateMember(String id, MemberDto dto) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Member not found with id: " + id));

        if (existingMember.getIsDeleted()) {
            throw new NoSuchElementException("Cannot update a deleted member: " + id);
        }

        // Check if email is being changed and if the new email already exists for another member
        if (!existingMember.getEmailAddress().equalsIgnoreCase(dto.getEmailAddress())) {
            memberRepository.findByEmailAddress(dto.getEmailAddress()).ifPresent(m -> {
                if (!m.getId().equals(id)) { // Make sure it's not the same member
                    throw new IllegalArgumentException("Email address already exists: " + dto.getEmailAddress());
                }
            });
        }

        // Build the updated member using the existing ID and data from DTO
         Member updatedMember = new Member.MemberBuilder(dto.getFirstName(), dto.getLastName(), dto.getEmailAddress())
                .id(existingMember.getId()) // Preserve the original ID
                .dateOfBirth(dto.getDateOfBirth())
                .ministriesOfInterest(dto.getMinistriesOfInterest())
                .primaryPhone(dto.getPrimaryPhone())
                .residingAddress(dto.getResidingAddress())
                .maritalStatus(dto.getMaritalStatus())
                .attendanceStatus(dto.getAttendanceStatus())
                .gender(dto.getGender())
                .emergencyContact(dto.getEmergencyContact())
                .emergencyContactRelationship(dto.getEmergencyContactRelationship())
                .occupation(dto.getOccupation())
                .skills(dto.getSkills())
                .lastAttendance(dto.getLastAttendance())
                .joinDate(dto.getJoinDate())
                .baptized(dto.getBaptized())
                .isDeleted(existingMember.getIsDeleted()) // Preserve existing isDeleted status
                .build();

        // Save the updated member; Spring Data MongoDB save() performs an upsert (update if ID exists)
        Member savedMember = memberRepository.save(updatedMember);
        return new MemberDto(savedMember);
    }

    // Implemented searchByName
    @Override
    public Page<MemberDto> searchByName(String name, Pageable pageable) {
        // Split name for potentially searching first and last names
        // Basic split, might need refinement
        String firstName = name;
        String lastName = name; // Default to searching both fields with the full string
        if (name.contains(" ")) {
            String[] parts = name.split(" ", 2);
            firstName = parts[0];
            lastName = parts[1];
        }
        // Use the repository method, searching name in both first and last name fields
        return memberRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseAndIsDeletedFalse(firstName, lastName, pageable)
                .map(MemberDto::new);
    }
}
