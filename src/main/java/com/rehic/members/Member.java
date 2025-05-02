package com.rehic.members;

import com.rehic.global.Gender;
import com.rehic.global.MaritalStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "members")
@Getter
@Setter
@NoArgsConstructor // Add this for MongoDB deserialization
public class Member {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate dateOfBirth;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String primaryPhone;
    private String emergencyContact;
    private String emergencyContactRelationship;
    private String occupation;
    private String residingAddress;
    private List<String> ministriesOfInterest;
    private List<String> skills;
    private AttendanceStatus attendanceStatus;
    private LocalDate lastAttendance;
    private LocalDate joinDate;
    private Boolean baptized;
    private Boolean isDeleted = false;

    // Keep the builder for creating new instances
    public static class MemberBuilder {
        private Member member;

        public MemberBuilder(String firstName, String lastName, String emailAddress) {
            member = new Member();
            member.firstName = firstName;
            member.lastName = lastName;
            member.emailAddress = emailAddress;
        }

        public MemberBuilder id(String id) {
            member.id = id;
            return this;
        }

        public MemberBuilder dateOfBirth(LocalDate dateOfBirth) {
            member.dateOfBirth = dateOfBirth;
            return this;
        }

        public MemberBuilder gender(Gender gender) {
            member.gender = gender;
            return this;
        }

        public MemberBuilder maritalStatus(MaritalStatus maritalStatus) {
            member.maritalStatus = maritalStatus;
            return this;
        }

        public MemberBuilder primaryPhone(String primaryPhone) {
            member.primaryPhone = primaryPhone;
            return this;
        }

        public MemberBuilder residingAddress(String residingAddress) {
            member.residingAddress = residingAddress;
            return this;
        }

        public MemberBuilder ministriesOfInterest(List<String> ministriesOfInterest) {
            member.ministriesOfInterest = ministriesOfInterest;
            return this;
        }

        public MemberBuilder attendanceStatus(AttendanceStatus attendanceStatus) {
            member.attendanceStatus = attendanceStatus;
            return this;
        }

        public MemberBuilder isDeleted(Boolean isDeleted) {
            member.isDeleted = isDeleted;
            return this;
        }


        public MemberBuilder emergencyContact(String emergencyContact) {
            member.emergencyContact = emergencyContact;
            return this;
        }

        public MemberBuilder occupation(String occupation) {
            member.occupation = occupation;
            return this;
        }

        public MemberBuilder emergencyContactRelationship(String emergencyContactRelationship) {
            member.emergencyContactRelationship = emergencyContactRelationship;
            return this;
        }

        public MemberBuilder skills(List<String> skills) {
            member.skills = skills;
            return this;
        }

        public MemberBuilder lastAttendance(LocalDate lastAttendance) {
            member.lastAttendance = lastAttendance;
            return this;
        }

        public MemberBuilder joinDate(LocalDate joinDate) {
            member.joinDate = joinDate;
            return this;
        }

        public MemberBuilder baptized(Boolean baptized) {
            member.baptized = baptized;
            return this;
        }

        public Member build() {
            return member;
        }
    }
}
