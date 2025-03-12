package com.lukman.stms.stms.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lukman.stms.stms.application.constant.ClassEnum;
import com.lukman.stms.stms.application.constant.StudentStatus;
import com.lukman.stms.stms.application.dto.request.RegStudentDto;
import com.lukman.stms.stms.application.dto.response.StudentResponseDto;
import com.lukman.stms.stms.application.dto.response.SuccessResponse;
import com.lukman.stms.stms.infrastructure.api.StudentServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/register")

    public ResponseEntity<SuccessResponse<RegStudentDto>> registerStudent(@RequestBody RegStudentDto student) {
        studentService.registerStudent(student);
        SuccessResponse<RegStudentDto> message = new SuccessResponse<RegStudentDto>(
                "Student Registration Successful",
                201,
                student);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/fetch-all")

    public ResponseEntity<SuccessResponse<List<StudentResponseDto>>> fetchAllStudent(
            @RequestParam(required = false) StudentStatus status,
            @RequestParam(required = false) String className,
            @RequestParam String session,
            @RequestParam(required = false) Integer term) {
        List<StudentResponseDto> students = new ArrayList<StudentResponseDto>();
        if (className == null && session != null && !session.isEmpty() && term != null) {
            students = studentService.getallStudent(session, term);
        } else if (term == null && session != null && !session.isEmpty()) {
            students = studentService.getallStudent(session);
        } else {
            students = studentService.getallStudent(ClassEnum.valueOf(className), session, term);
        }

        SuccessResponse<List<StudentResponseDto>> message = new SuccessResponse<List<StudentResponseDto>>(
                "Student fetched Successful",
                200,
                students);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
