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

    private ClassEnum className;
    // String allias;
    private int term;
    private String teacher;
    @NotNull(message = "Session cannot be null")
    private String session;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;

    public StudentClassDto(ClassEnum className, int term, String session) {
        this.className = className;
        this.term = term;
        this.session = session;
    }

    public StudentClassDto(@NotNull(message = "Session cannot be null") String session) {
        this.session = session;
    }

}
