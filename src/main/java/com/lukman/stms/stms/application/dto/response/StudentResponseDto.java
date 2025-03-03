package com.lukman.stms.stms.application.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lukman.stms.stms.application.constant.Gender;
import com.lukman.stms.stms.application.dto.request.StudentGuardianDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentResponseDto {
    private String id;
    private String surName;
    private String firstName;
    private String middleName;
    private String passportUrl;
    private Gender gender;
    private String homeAddress;
    private String currentClass;
    private String session;
    private int term;
    private List<StudentGuardianDto> guardian;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate registrationDate;

    private String admissionNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyyw")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime updatedAt;
}
