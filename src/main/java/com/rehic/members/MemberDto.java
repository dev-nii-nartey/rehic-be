package com.rehic.members;

import com.rehic.global.Gender;
import com.rehic.global.MaritalStatus;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class MemberDto {
    private final String recordId;
    private final String firstName;
    private final String lastName;
    private final String dateOfBirth;
    private final Gender gender;
    private final MaritalStatus maritalStatus;
    private final String residingAddress;
    private final String primaryPhone;
    private final AttendanceStatus attendanceStatus;
    private final List<String> ministriesOfInterest;
    private final boolean isDeleted;
    private final String emailAddress;


    public MemberDto(Member member) {
        recordId = member.getId();
        firstName = member.getFirstName();
        lastName = member.getLastName();
        dateOfBirth = member.getDateOfBirth();
        gender = member.getGender();
        maritalStatus = member.getMaritalStatus();
        residingAddress = member.getResidingAddress();
        primaryPhone = member.getPrimaryPhone();
        attendanceStatus = member.getAttendanceStatus();
        ministriesOfInterest = member.getMinistriesOfInterest();
        emailAddress = member.getEmailAddress();
        isDeleted = member.getIsDeleted();
    }
}
