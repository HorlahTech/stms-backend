package com.lukman.stms.stms.service;

import java.util.List;

import com.lukman.stms.stms.application.dto.request.StudentClassDto;
import com.lukman.stms.stms.application.dto.request.UpdateTermDto;
import com.lukman.stms.stms.infrastructure.exception.UnknownException;
import com.lukman.stms.stms.models.FeesStructureJ;

public interface StudentClassService {
    void sessionSetUp(StudentClassDto session) throws UnknownException;

    List<StudentClassDto> getallSessionClass(String session);

    public void updateTermDate(UpdateTermDto terms);

    public List<FeesStructureJ> fetchFees();

}
