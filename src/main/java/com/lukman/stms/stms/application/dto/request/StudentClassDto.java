package com.lukman.stms.stms.application.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lukman.stms.stms.application.constant.ClassEnum;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentClassDto {

    private ClassEnum name;
    // String allias;
    private int term;
    private String teacher;
    @NotNull(message = "Session cannot be null")
    private String session;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    public StudentClassDto(ClassEnum name, int term, String session) {
        this.name = name;
        this.term = term;
        this.session = session;
    }

    public StudentClassDto(@NotNull(message = "Session cannot be null") String session) {
        this.session = session;
    }

}
