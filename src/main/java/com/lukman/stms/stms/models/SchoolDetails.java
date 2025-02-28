package com.lukman.stms.stms.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SchoolDetails {
    @Id
    private String id;
    @Indexed
    private String code;
    private String emailAddress;
    private String phoneNumber;
    private String name;
    private String address;
    private String state;
    private String localGovernment;
    private Boolean isGovernmentApproved;
    private String approvalCode;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
