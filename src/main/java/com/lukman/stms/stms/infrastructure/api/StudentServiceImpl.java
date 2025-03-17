package com.lukman.stms.stms.infrastructure.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukman.stms.stms.application.appconfig.SchoolContext;
import com.lukman.stms.stms.application.constant.ClassEnum;
import com.lukman.stms.stms.application.constant.StudentStatus;
import com.lukman.stms.stms.application.dto.request.RegStudentDto;
import com.lukman.stms.stms.application.dto.response.StudentResponseDto;
import com.lukman.stms.stms.infrastructure.exception.AllCustomExceptionHandler;
import com.lukman.stms.stms.infrastructure.exception.ConflictException;
import com.lukman.stms.stms.infrastructure.exception.EmptyFieldException;
import com.lukman.stms.stms.infrastructure.exception.UserNotFoundException;
import com.lukman.stms.stms.infrastructure.repository.StudentClassRepository;
import com.lukman.stms.stms.infrastructure.repository.StudentRepository;
import com.lukman.stms.stms.infrastructure.repository.StudentTermRepository;
import com.lukman.stms.stms.models.StudentClass;
import com.lukman.stms.stms.models.StudentJ;
import com.lukman.stms.stms.models.StudentTermDataJ;
import com.lukman.stms.stms.service.StudentService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor

@Slf4j
public class StudentServiceImpl implements StudentService {

    private StudentRepository repository;

    private StudentTermRepository studentTermRepo;

    private StudentClassRepository studentClassRepo;

    private ModelMapper modelMapper;

    private ObjectMapper objectMapper;

    @Override
    public void registerStudent(RegStudentDto student) {
        if (student.getFirstName() == null) {
            throw new EmptyFieldException("Student First Name Cannot be Empty");
        }
        if (student.getSurName() == null) {
            throw new EmptyFieldException("Student Surname  Cannot be Empty");
        }
        if (student.getClassName() == null) {
            throw new EmptyFieldException("Student Class  Cannot be Empty");
        }
        if (student.getTerm() < 1 || student.getTerm() > 3) {
            throw new EmptyFieldException("The term must be within 1 and 3");
        }
        if (student.getSession() == null) {
            throw new EmptyFieldException("Session Cannot be Empty");
        }
        // System.out.println("Checking: " + student.getSurName() + ", " +
        // student.getFirstName()
        // + ", " + student.getMiddleName() + ", " + student.getHomeAddress()
        // + ", " + SchoolContext.getSchoolCode());
        boolean doesExist = repository.existsBySurNameAndFirstNameAndMiddleNameAndHomeAddressAndSchoolCode(
                student.getSurName(),
                student.getFirstName(), student.getMiddleName(), student.getHomeAddress(),
                SchoolContext.getSchoolCode());
        if (doesExist) {
            throw new ConflictException("Student Already exist");
        }

        // StudentJ newStudent = new StudentJ();
        // StudentTermDataJ studentTerm = new StudentTermDataJ();
        StudentJ newStudent = modelMapper.map(student, StudentJ.class);
        StudentClass clas = studentClassRepo.findByClassNameAndTermAndSessionAndSchoolCode(student.getClassName(),
                student.getTerm(),
                student.getSession(), SchoolContext.getSchoolCode())
                .orElseThrow(() -> new UserNotFoundException("Class Not found"));

        if (newStudent.getRegistrationDate() == null) {
            newStudent.setRegistrationDate(LocalDate.now());
        }
        newStudent.setStatus(StudentStatus.active);
        newStudent.setSchoolCode(SchoolContext.getSchoolCode());

        final StudentJ savedStudent = repository.save(newStudent);

        studentTermRepo
                .save(new StudentTermDataJ(SchoolContext.getSchoolCode(), savedStudent.getId(), clas.getId(),
                        clas.getSession(), clas.getTerm()));

    }

    @Override
    public List<StudentResponseDto> getallStudent(String session, int term) {

        List<StudentTermDataJ> terms = studentTermRepo.findBySessionNameAndTermAndSchoolCode(session, term,
                SchoolContext.getSchoolCode());

        return terms.stream().map(_term -> {
            final StudentJ student = repository.findById(_term.getStudentId())
                    .orElseThrow(() -> new UserNotFoundException(
                            String.format("Student with the Id: %s not found", _term.getStudentId())));
            final StudentResponseDto dto = modelMapper.map(student, StudentResponseDto.class);
            dto.setSession(_term.getSessionName());
            dto.setTerm(_term.getTerm());

            return dto;
        }).collect(Collectors.toList());

    }

    @Override
    public List<StudentResponseDto> getallStudent(StudentStatus status, String session, int term) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getallStudent'");
    }

    @Override
    public List<StudentResponseDto> getallStudent(ClassEnum classname, String session, int term) {
        if (classname == null) {
            throw new EmptyFieldException("Class Name cannot be empty");
        }
        if (session == null) {
            throw new EmptyFieldException("Session Name cannot be empty");
        }
        final StudentClass _class = studentClassRepo
                .findByClassNameAndTermAndSessionAndSchoolCode(classname.name(), term,
                        session, SchoolContext.getSchoolCode())
                .orElseThrow(() -> new UserNotFoundException("Class Not Found"));
        final List<StudentTermDataJ> termData = studentTermRepo.findByClassIdAndSchoolCode(_class.getId(),
                SchoolContext.getSchoolCode());
        List<StudentResponseDto> students = termData.stream().map(termDataJ -> {
            StudentJ student = repository.findById(termDataJ.getStudentId())
                    .orElseThrow(() -> new UserNotFoundException(
                            String.format("Student with the id: %d not found", termDataJ.getStudentId())));
            final StudentResponseDto studentDto = modelMapper.map(student, StudentResponseDto.class);
            studentDto.setCurrentClass(_class.getClassName());
            studentDto.setSession(_class.getSession());
            studentDto.setTerm(_class.getTerm());
            return studentDto;
        }).collect(Collectors.toList());

        return students;
    }

}
