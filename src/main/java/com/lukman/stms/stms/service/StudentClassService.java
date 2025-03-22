package com.lukman.stms.stms.service;

import java.util.List;

import com.lukman.stms.stms.application.dto.request.StudentClassDto;
import com.lukman.stms.stms.application.dto.request.FeesDto;
import com.lukman.stms.stms.application.dto.request.RegisterSchoolDto;
import com.lukman.stms.stms.application.dto.request.SessionDto;
import com.lukman.stms.stms.infrastructure.exception.UnknownException;
import com.lukman.stms.stms.models.FeesStructureJ;

public interface StudentClassService {
    void sessionSetUp(StudentClassDto session) throws UnknownException;

    RegisterSchoolDto SchoolSetUp(RegisterSchoolDto session) throws UnknownException;

    List<StudentClassDto> getallSessionClass(String session);

    public SessionDto getSession(String session);

    public void startTerm(String session, int term);

    public void updateTermDate(SessionDto terms);

    public FeesDto createFee(FeesDto fee);

    public FeesDto editFee(FeesDto fee);

    public List<FeesDto> fetchAllFees(String session);

    public List<FeesDto> fetchAllFees(String session, int term);

}
