package com.lukman.stms.stms.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class StudentTermDataJ {
    @Id
    private String id;
    private String studentId;
    private String classId;
    private int lastTermDebt;
    private int currentTermBalance;
    private int amountPaid;
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public StudentTermDataJ(String studentId, String classId) {
        this.studentId = studentId;
        this.classId = classId;
    }
    

}
