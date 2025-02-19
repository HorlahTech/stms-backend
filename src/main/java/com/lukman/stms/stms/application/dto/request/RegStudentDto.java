package com.lukman.stms.stms.application.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lukman.stms.stms.application.constant.Gender;
import com.lukman.stms.stms.models.StudentClass;
import com.lukman.stms.stms.models.StudentGuardian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegStudentDto {
    private String surName;
    private String firstName;
    private String middleName;
    private String passportId;
    private Gender gender;
    private String homeAddress;
    private String className;
    private String session;
    private int term;
    private List<StudentGuardianDto> guardian;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate registrationDate;
    private double height;
    private int admissionNumber;

}
