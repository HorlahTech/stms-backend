// package com.lukman.stms.stms.application.dto.request;

package com.lukman.stms.stms.application.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterSchoolDto {

    private String code;
    private String emailAddress;
    private String phoneNumber;
    private String name;
    private String address;
    private String state;
    private String city;
    private String slogan;
    private String localGovernment;
    private Boolean isGovernmentApproved;
    private String approvalCode;

}
