package com.lukman.stms.stms.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTermDataJ {
    @Id
    private String id;
    private String studentId;
    private String classId;
    private String sessionName;
    private int lastTermDebt;
    private int term;
    private int currentTermBalance;
    private int amountPaid;
    private String schoolCode;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public StudentTermDataJ(String schoolCode, String studentId, String classId, String session, int term) {
        this.schoolCode = schoolCode;
        this.studentId = studentId;

        this.classId = classId;
        this.sessionName = session;
        this.term = term;
    }

}
