package com.lukman.stms.stms.infrastructure.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.NameNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukman.stms.stms.application.constant.ClassEnum;
import com.lukman.stms.stms.application.constant.StudentStatus;
import com.lukman.stms.stms.application.dto.request.RegStudentDto;
import com.lukman.stms.stms.application.dto.response.StudentResponseDto;
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

@Service
@AllArgsConstructor
// @Slf4j
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
        } else if (student.getSurName() == null) {
            throw new EmptyFieldException("Student Surname  Cannot be Empty");
        } else if (student.getClassName() == null) {
            throw new EmptyFieldException("Student Class  Cannot be Empty");
        } else if (student.getTerm() < 1 || student.getTerm() > 3) {
            throw new EmptyFieldException("The term must be within 1 and 3");
        } else if (student.getSession() == null) {
            throw new EmptyFieldException("Session Cannot be Empty");
        }

        // StudentJ newStudent = new StudentJ();
        // StudentTermDataJ studentTerm = new StudentTermDataJ();
        StudentJ newStudent = modelMapper.map(student, StudentJ.class);
        StudentClass clas = studentClassRepo.findByClassNameAndTermAndSession(student.getClassName(),
                student.getTerm(),
                student.getSession()).orElseThrow(() -> new UserNotFoundException("Class Not found"));

        if (newStudent.getRegistrationDate() == null) {
            newStudent.setRegistrationDate(LocalDate.now());
        }
        newStudent.setStatus(StudentStatus.active);

        final StudentJ savedStudent = repository.save(newStudent);

        studentTermRepo.save(new StudentTermDataJ(savedStudent.getId(), clas.getId()));

    }

    // @Override
    // public List<StudentResponseDto> getallStudent(String className, String
    // session, int term) {
    // // StudentResponseDto
    // if (status == null) {
    // status = StudentStatus.active;
    // }
    // final StudentClass _class =
    // studentClassRepo.findByClassNameAndTermAndSession();
    // List<StudentJ> response = repository.findByStatus(status);
    // final List<StudentResponseDto> dto = new ArrayList<StudentResponseDto>();
    // for (StudentJ student : response) {
    // final StudentResponseDto studentDto = modelMapper.map(student,
    // StudentResponseDto.class);
    // studentTermRepo.findByStudentId(student.getId());

    // studentDto.

    // dto.add(modelMapper.map(student, StudentResponseDto.class));
    // }

    // return dto;
    // }

    @Override
    public List<StudentResponseDto> getallStudent(String session, int term) {
        final List<StudentClass> _classes = studentClassRepo.findBySessionAndTerm(session, term);
        // System.out.println(_classes);
        final List<StudentResponseDto> studentDto = new ArrayList<StudentResponseDto>();
        for (StudentClass _class : _classes) {
            final List<StudentTermDataJ> termData = studentTermRepo.findByClassId(_class.getId());

            System.out.println(termData);
            List<StudentResponseDto> students = termData.stream().map(_term -> {
                final StudentJ student = repository.findById(_term.getStudentId())
                        .orElseThrow(() -> new UserNotFoundException(
                                String.format("Student with the Id: %s not found", _term.getStudentId())));
                final StudentResponseDto dto = modelMapper.map(student, StudentResponseDto.class);
                dto.setSession(_class.getSession());
                dto.setCurrentClass(_class.getClassName());
                dto.setTerm(_class.getTerm());

                return dto;
            }).collect(Collectors.toList());
            studentDto.addAll(students);

        }

        return studentDto;
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
        final StudentClass _class = studentClassRepo.findByClassNameAndTermAndSession(classname.name(), term,
                session).orElseThrow(() -> new UserNotFoundException("Class Not Found"));
        final List<StudentTermDataJ> termData = studentTermRepo.findByClassId(_class.getId());
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
