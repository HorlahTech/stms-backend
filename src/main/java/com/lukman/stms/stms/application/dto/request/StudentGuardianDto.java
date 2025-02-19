package com.lukman.stms.stms.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentGuardianDto {
    private String fullName;
    private String address;
    private String relationShip;
    private String phoneNumber;
    private String altphoneNumber;
    private String emailAddress;

}
