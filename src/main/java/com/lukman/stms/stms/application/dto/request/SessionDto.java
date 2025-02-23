package com.lukman.stms.stms.application.dto.request;

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
public class SessionDto {
    private String session;
    private TermDto firstTerm;
    private TermDto secondTerm;
    private TermDto thirdTerm;

    public SessionDto(String session, TermDto firstTerm) {
        this.session = session;
        this.firstTerm = firstTerm;
    }

    public SessionDto(String session, TermDto firstTerm, TermDto secondTerm) {
        this.session = session;
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

}
