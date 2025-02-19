package com.lukman.stms.stms.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lukman.stms.stms.application.constant.StudentStatus;
import com.lukman.stms.stms.application.dto.request.RegStudentDto;
import com.lukman.stms.stms.application.dto.response.SuccessResponse;
import com.lukman.stms.stms.infrastructure.api.StudentServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/student")
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

    public ResponseEntity<SuccessResponse<List<RegStudentDto>>> fetchAllStudent(
            @RequestParam(required = false) StudentStatus status) {
        List<RegStudentDto> students = studentService.getallStudent(status);
        SuccessResponse<List<RegStudentDto>> message = new SuccessResponse<List<RegStudentDto>>(
                "Student fetched Successful",
                200,
                students);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
