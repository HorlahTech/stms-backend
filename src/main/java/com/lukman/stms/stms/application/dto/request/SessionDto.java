
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

    public SessionDto(String session, TermDto termDto) {
        this.session = session;
        if (termDto.getTerm() == 1) {
            this.firstTerm = termDto;
        } else if (termDto.getTerm() == 2) {
            this.secondTerm = termDto;
        } else {
            this.thirdTerm = termDto;
        }

    }

    public SessionDto(String session, TermDto termOne, TermDto termTwo) {
        this.session = session;
        if (termOne.getTerm() == 1) {
            this.firstTerm = termOne;
        } else if (termOne.getTerm() == 2) {
            this.secondTerm = termOne;
        } else {
            this.thirdTerm = termOne;
        }
        if (termTwo.getTerm() == 1) {
            this.firstTerm = termTwo;
        } else if (termTwo.getTerm() == 2) {
            this.secondTerm = termTwo;
        } else {
            this.thirdTerm = termTwo;
        }

    }

}
