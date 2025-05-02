package com.rehic.members;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberDtoSummary {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String primaryPhone;
    private final String emailAddress;
    private final LocalDate joinDate;
    private final AttendanceStatus attendanceStatus;
    private final LocalDate lastAttendance;

    public MemberDtoSummary(Member member) {
        this.id = member.getId();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.primaryPhone = member.getPrimaryPhone();
        this.emailAddress = member.getEmailAddress();
        this.joinDate = member.getJoinDate();
        this.attendanceStatus = member.getAttendanceStatus();
        this.lastAttendance = member.getLastAttendance();
    }
}
