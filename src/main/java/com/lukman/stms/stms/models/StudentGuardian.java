package com.lukman.stms.stms.models;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StudentGuardian {
    @Id
    private String id;
    private String fullName;
    private String address;
    private String relationShip;
    private String phoneNumber;
    private String altphoneNumber;

}
