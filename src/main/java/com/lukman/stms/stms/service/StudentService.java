package com.lukman.stms.stms.service;

import java.util.List;

import com.lukman.stms.stms.application.constant.ClassEnum;
import com.lukman.stms.stms.application.constant.StudentStatus;
import com.lukman.stms.stms.application.dto.request.RegStudentDto;
import com.lukman.stms.stms.application.dto.response.StudentResponseDto;

public interface StudentService {
    public void registerStudent(RegStudentDto student);

    public List<StudentResponseDto> getallStudent(StudentStatus status, String session, int term);

    public List<StudentResponseDto> getallStudent(ClassEnum classname, String session, int term);

    public List<StudentResponseDto> getallStudent(String session, int term);

}
