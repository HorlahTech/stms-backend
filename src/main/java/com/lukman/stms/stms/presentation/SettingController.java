package com.lukman.stms.stms.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lukman.stms.stms.application.dto.request.StudentClassDto;
import com.lukman.stms.stms.application.dto.request.RegisterSchoolDto;
import com.lukman.stms.stms.application.dto.request.SessionDto;
import com.lukman.stms.stms.application.dto.response.EmptyReponseDto;
import com.lukman.stms.stms.application.dto.response.SuccessResponse;
import com.lukman.stms.stms.infrastructure.exception.UnknownException;
import com.lukman.stms.stms.service.StudentClassService;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/settings")
public class SettingController {
    @Autowired
    StudentClassService service;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/session")
    public ResponseEntity<SuccessResponse<EmptyReponseDto>> sessionSetUp(@RequestBody StudentClassDto settion)
            throws UnknownException {
        logger.info("sessionSetUp call");
        service.sessionSetUp(settion);
        logger.info("sessionSetUp and");
        final SuccessResponse<EmptyReponseDto> res = new SuccessResponse<EmptyReponseDto>(
                "Session Created Successfully", 201);
        logger.info("sessionSetUp dell");
        return ResponseEntity.status(201).body(res);
    }

    @GetMapping("/session/classes")
    public ResponseEntity<SuccessResponse<List<StudentClassDto>>> getallSessionClassess(
            @RequestParam String sessionName) {
        logger.info("getallSessionClassess call");
        final List<StudentClassDto> response = service.getallSessionClass(sessionName);
        final SuccessResponse<List<StudentClassDto>> res = new SuccessResponse<>("Recored Fetched Sucessfully", 200,
                response);
        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("/session")
    public ResponseEntity<SuccessResponse<SessionDto>> getSession(
            @RequestParam String sessionName) {
        logger.info("getallSession call");
        final SessionDto response = service.getSession(sessionName);
        final SuccessResponse<SessionDto> res = new SuccessResponse<>("Recored Fetched Sucessfully", 200,
                response);
        return ResponseEntity.status(200).body(res);
    }

    @PatchMapping("/update-term")
    public ResponseEntity<SuccessResponse> upadateTermDate(
            @RequestBody SessionDto updateTermDto) {
        logger.info("upadateTermDate call");
        service.updateTermDate(updateTermDto);
        final SuccessResponse res = new SuccessResponse<>("Update Successfully", 200);
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping("/school")
    public ResponseEntity<SuccessResponse<RegisterSchoolDto>> registerSchool(
            @RequestBody RegisterSchoolDto schoolEntity) {
        RegisterSchoolDto school = service.SchoolSetUp(schoolEntity);
        final SuccessResponse<RegisterSchoolDto> res = new SuccessResponse<RegisterSchoolDto>("Update Successfully",
                200, school);
        return ResponseEntity.status(201).body(res);
    }

}
