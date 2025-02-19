package com.lukman.stms.stms.service;

import java.util.List;

import com.lukman.stms.stms.application.constant.StudentStatus;
import com.lukman.stms.stms.application.dto.request.RegStudentDto;

public interface StudentService {
    public void registerStudent(RegStudentDto student);

    public List<RegStudentDto> getallStudent(StudentStatus status);

}
