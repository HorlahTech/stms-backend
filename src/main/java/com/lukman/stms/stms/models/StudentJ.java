package com.lukman.stms.stms.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lukman.stms.stms.application.constant.Gender;
import com.lukman.stms.stms.application.constant.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
@CompoundIndex(name = "class_term_idx", def = "{'surName': 1, 'firstName': 1, 'middleName': 1}")
public class StudentJ {
    @Id
    private String id;
    private String surName;
    private String passportUrl;
    private String firstName;
    private String middleName;
    private Gender gender;
    private String homeAddress;
    private String schoolCode;
    // private String classId;
    private StudentStatus status;
    private List<StudentGuardian> guardian;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate registrationDate;

    @Indexed(unique = true)
    private String admissionNumber;
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
