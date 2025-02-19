package com.lukman.stms.stms.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateTermDto {
    private String session;
    private TermDto firstTerm;
    private TermDto secondTerm;
    private TermDto thirdTerm;
    public UpdateTermDto(String session, TermDto firstTerm) {
        this.session = session;
        this.firstTerm = firstTerm;
    }
    public UpdateTermDto(String session, TermDto firstTerm, TermDto secondTerm) {
        this.session = session;
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }
   
    
}
