package com.lukman.stms.stms.service;

import java.util.List;

import com.lukman.stms.stms.application.dto.request.StudentClassDto;
import com.lukman.stms.stms.application.dto.request.RegisterSchoolDto;
import com.lukman.stms.stms.application.dto.request.SessionDto;
import com.lukman.stms.stms.infrastructure.exception.UnknownException;
import com.lukman.stms.stms.models.FeesStructureJ;

public interface StudentClassService {
    void sessionSetUp(StudentClassDto session) throws UnknownException;

    RegisterSchoolDto SchoolSetUp(RegisterSchoolDto session) throws UnknownException;

    List<StudentClassDto> getallSessionClass(String session);

    SessionDto getSession(String session);

    public void updateTermDate(SessionDto terms);

    public List<FeesStructureJ> fetchFees();

}
