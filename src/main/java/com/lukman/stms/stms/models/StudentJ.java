package com.lukman.stms.stms.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lukman.stms.stms.application.constant.Gender;
import com.lukman.stms.stms.application.constant.StudentStatus;
import com.lukman.stms.stms.application.dto.request.StudentGuardianDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentJ {
    @Id
    private String id;
    private String surName;
    private String passportId;
    private String firstName;
    private String middleName;
    private Gender gender;
    private String homeAddress;
    private String classId;
    private StudentStatus status;
    private List<StudentGuardianDto> guardian;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate registrationDate;
    private double height;
    private String admissionNumber;

}
