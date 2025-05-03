package com.rehic.members;

import com.rehic.global.Gender;
import com.rehic.global.MaritalStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    private String recordId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private MaritalStatus maritalStatus;
    @NotBlank
    private String residingAddress;
    @NotBlank
    private String primaryPhone;
    private AttendanceStatus attendanceStatus;
    private List<String> ministriesOfInterest;
    private boolean isDeleted;
    @NotBlank
    private String emailAddress;
    private String emergencyContact;
    private String emergencyContactRelationship;
    private String occupation;
    private List<String> skills;
    private LocalDate lastAttendance;
    private LocalDate joinDate;
    private Boolean baptized;

    public MemberDto(Member member) {
        this.recordId = member.getId();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.dateOfBirth = member.getDateOfBirth();
        this.gender = member.getGender();
        this.maritalStatus = member.getMaritalStatus();
        this.residingAddress = member.getResidingAddress();
        this.primaryPhone = member.getPrimaryPhone();
        this.attendanceStatus = member.getAttendanceStatus();
        this.ministriesOfInterest = member.getMinistriesOfInterest();
        this.emailAddress = member.getEmailAddress();
        this.isDeleted = member.getIsDeleted();
        this.emergencyContact = member.getEmergencyContact();
        this.emergencyContactRelationship = member.getEmergencyContactRelationship();
        this.occupation = member.getOccupation();
        this.skills = member.getSkills();
        this.lastAttendance = member.getLastAttendance();
        this.joinDate = member.getJoinDate();
        this.baptized = member.getBaptized();
    }
}
