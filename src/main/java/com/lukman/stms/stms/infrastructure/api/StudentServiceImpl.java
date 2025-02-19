package com.lukman.stms.stms.infrastructure.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukman.stms.stms.application.constant.StudentStatus;
import com.lukman.stms.stms.application.dto.request.RegStudentDto;
import com.lukman.stms.stms.application.dto.request.TermDto;
import com.lukman.stms.stms.application.dto.request.UpdateTermDto;
import com.lukman.stms.stms.infrastructure.exception.EmptyFieldException;
import com.lukman.stms.stms.infrastructure.repository.StudentClassRepository;
import com.lukman.stms.stms.infrastructure.repository.StudentRepository;
import com.lukman.stms.stms.models.StudentClass;
import com.lukman.stms.stms.models.StudentJ;
import com.lukman.stms.stms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;
    @Autowired
    private StudentClassRepository studentClassRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void registerStudent(RegStudentDto student) {
        if (student.getFirstName() == null) {
            throw new EmptyFieldException("Student First Name Cannot be Empty");
        } else if (student.getSurName() == null) {
            throw new EmptyFieldException("Student Surname  Cannot be Empty");
        } else if (student.getClass() == null) {
            throw new EmptyFieldException("Student Class  Cannot be Empty");
        } else if (student.getTerm() < 1 && student.getTerm() > 3) {
            throw new EmptyFieldException("The term must be within 1 and 3");
        } else if (student.getSession() == null) {
            throw new EmptyFieldException("Session Cannot be Empty");
        }

        StudentJ newStudent = new StudentJ();
        Optional<StudentClass> clas = studentClassRepo.findByNameAndTermAndSession(student.getClassName(),
                student.getTerm(),
                student.getSession());
        if (clas.isPresent()) {
            String classId = clas.get().getId();
            newStudent.setClassId(classId);
        }
        modelMapper.map(student, newStudent);
        if (newStudent.getRegistrationDate() == null) {
            newStudent.setRegistrationDate(LocalDate.now());
        }
        newStudent.setStatus(StudentStatus.active);
        repository.save(newStudent);
    }

    @Override
    public List<RegStudentDto> getallStudent(StudentStatus status) {

        if (status == null) {
            status = StudentStatus.active;
        }
        List<StudentJ> response = repository.findByStatus(status);
        final List<RegStudentDto> dto = new ArrayList<RegStudentDto>();
        modelMapper.map(response, dto);
        return dto;
    }

   

}
