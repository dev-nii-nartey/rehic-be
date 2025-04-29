package com.rehic.members;

import com.rehic.global.Gender;
import com.rehic.global.MaritalStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "membersTable")
public class Member {
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
    @Indexed(unique = true)
    private final String emailAddress;
    @Id
    private String id;

    private Member(MemberBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;
        this.gender = builder.gender;
        this.residingAddress = builder.residingAddress;
        this.maritalStatus = builder.maritalStatus;
        this.primaryPhone = builder.primaryPhone;
        this.emailAddress = builder.emailAddress;
        this.attendanceStatus = builder.status;
        this.ministriesOfInterest = builder.ministriesOfInterest;
        this.isDeleted = builder.isDeleted;
    }

    @Override
    public String toString() {
        return "FirstName: " + firstName + " and " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public String getResidingAddress() {
        return residingAddress;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public AttendanceStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public String getId() {
        return id;
    }

    public List<String> getMinistriesOfInterest() {
        return ministriesOfInterest;
    }

    public static class MemberBuilder {
        private String id;
        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private Gender gender;
        private MaritalStatus maritalStatus;
        private String residingAddress;
        private String primaryPhone;
        private String emailAddress;
        private AttendanceStatus status = AttendanceStatus.ACTIVE;
        private List<String> ministriesOfInterest;
        private boolean isDeleted = false;

        public MemberBuilder() {
        }

        public MemberBuilder(String firstName, String lastName, String emailAddress) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.emailAddress = emailAddress;
        }

        public MemberBuilder id(String id) {
            this.id = id;
            return this;
        }

        public MemberBuilder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public MemberBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public MemberBuilder maritalStatus(MaritalStatus maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public MemberBuilder primaryPhone(String phone) {
            this.primaryPhone = phone;
            return this;
        }

        public MemberBuilder residingAddress(String residingAddress) {
            this.residingAddress = residingAddress;
            return this;
        }

        public MemberBuilder ministriesOfInterest(List<String> ministryOfInterest) {
            this.ministriesOfInterest = ministryOfInterest;
            return this;
        }

        public MemberBuilder attendanceStatus(AttendanceStatus status) {
            this.status = status;
            return this;
        }

        public MemberBuilder isDeleted(boolean status) {
            this.isDeleted = status;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }
}
