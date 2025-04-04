package com.lukman.stms.stms.infrastructure.api;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.lukman.stms.stms.application.appconfig.SchoolContext;
import com.lukman.stms.stms.application.constant.Applicability;
import com.lukman.stms.stms.application.constant.ClassEnum;
import com.lukman.stms.stms.application.constant.StudentStatus;
import com.lukman.stms.stms.application.dto.request.StudentClassDto;
import com.lukman.stms.stms.application.dto.request.TermDto;
import com.lukman.stms.stms.application.dto.request.FeesDto;
import com.lukman.stms.stms.application.dto.request.RegisterSchoolDto;
import com.lukman.stms.stms.application.dto.request.SessionDto;
import com.lukman.stms.stms.infrastructure.exception.ConflictException;
import com.lukman.stms.stms.infrastructure.exception.EmptyFieldException;
import com.lukman.stms.stms.infrastructure.exception.UnknownException;
import com.lukman.stms.stms.infrastructure.exception.UserNotFoundException;
import com.lukman.stms.stms.infrastructure.repository.FeesStructureRepository;
import com.lukman.stms.stms.infrastructure.repository.SchoolDetailsRepository;
import com.lukman.stms.stms.infrastructure.repository.StudentClassRepository;
import com.lukman.stms.stms.infrastructure.repository.StudentRepository;
import com.lukman.stms.stms.infrastructure.repository.StudentTermRepository;
import com.lukman.stms.stms.models.FeesStructureJ;
import com.lukman.stms.stms.models.SchoolDetails;
import com.lukman.stms.stms.models.StudentClass;
import com.lukman.stms.stms.models.StudentJ;
import com.lukman.stms.stms.models.StudentTermDataJ;
import com.lukman.stms.stms.service.StudentClassService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentClassImpl implements StudentClassService {

  private StudentClassRepository studentClassRepository;
  private SchoolDetailsRepository schoolRepository;
  private ModelMapper modelMapper;
  private FeesStructureRepository feesRepo;
  private StudentTermRepository termdata;
  private StudentRepository studentsRepo;
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public void sessionSetUp(StudentClassDto session) {

    if (session.getSession() == null || session.getSession().isBlank() || session.getSession().isEmpty()) {
      throw new EmptyFieldException("Session Name Cannot be null");
    }
    if (studentClassRepository.existsBySessionAndSchoolCode(session.getSession(), SchoolContext.getSchoolCode())) {
      throw new ConflictException("Session Already Exist");
    }
    List<StudentClass> classList = new ArrayList<>();
    for (int i = 1; i <= 3; i++) {
      for (int j = 0; j < ClassEnum.values().length; j++) {

        StudentClassDto studentClassDto = new StudentClassDto(ClassEnum.values()[j], i, session.getSession());
        StudentClass _class = modelMapper.map(studentClassDto, StudentClass.class);
        _class.setSchoolCode(SchoolContext.getSchoolCode());
        classList.add(_class);
      }
    }

    studentClassRepository.saveAll(classList);
  }

  @Override
  public List<StudentClassDto> getallSessionClass(String session) {
    if (session == null || session.isBlank() || session.isEmpty()) {
      throw new EmptyFieldException("Session Name Cannot be null");
    }
    final List<StudentClass> response = studentClassRepository.findBySessionAndSchoolCode(session,
        SchoolContext.getSchoolCode());

    java.lang.reflect.Type listType = new TypeToken<List<StudentClassDto>>() {
    }.getType();
    List<StudentClassDto> entityToDto = modelMapper.map(response, listType);
    return entityToDto;
    // return modelMapper.map(response, StudentClassDto.class);
  }

  @Override
  public void updateTermDate(SessionDto updateTermDto) {
    if (updateTermDto.getSession() == null || updateTermDto.getSession().isBlank()
        || updateTermDto.getSession().isEmpty()) {
      throw new EmptyFieldException("Session can not be Empty");
    }

    // Update First term start Date
    if (updateTermDto.getFirstTerm() != null && updateTermDto.getFirstTerm().getStartDate() != null) {
      studentClassRepository.updateClassesStartDateBySessionAndTermAndSchoolCode(
          updateTermDto.getSession(), 1,
          updateTermDto.getFirstTerm().getStartDate(), SchoolContext.getSchoolCode());

    }
    // Update First term end Date
    if (updateTermDto.getFirstTerm() != null && updateTermDto.getFirstTerm().getEndDate() != null) {
      studentClassRepository.updateClassesEndDateBySessionAndTermAndSchoolCode(updateTermDto.getSession(), 1,
          updateTermDto.getFirstTerm().getEndDate(), SchoolContext.getSchoolCode());

    }
    // Update Second term start Date
    if (updateTermDto.getSecondTerm() != null && updateTermDto.getSecondTerm().getStartDate() != null) {
      studentClassRepository.updateClassesStartDateBySessionAndTermAndSchoolCode(updateTermDto.getSession(), 2,
          updateTermDto.getSecondTerm().getStartDate(), SchoolContext.getSchoolCode());

    }
    // Update Second term end Date
    if (updateTermDto.getSecondTerm() != null && updateTermDto.getSecondTerm().getEndDate() != null) {
      studentClassRepository.updateClassesEndDateBySessionAndTermAndSchoolCode(updateTermDto.getSession(), 2,
          updateTermDto.getSecondTerm().getEndDate(), SchoolContext.getSchoolCode());

    }
    // Update Third term start Date
    if (updateTermDto.getThirdTerm() != null && updateTermDto.getThirdTerm().getStartDate() != null) {
      studentClassRepository.updateClassesStartDateBySessionAndTermAndSchoolCode(updateTermDto.getSession(), 3,
          updateTermDto.getThirdTerm().getStartDate(), SchoolContext.getSchoolCode());

    }
    // Update Third term end Date
    if (updateTermDto.getThirdTerm() != null && updateTermDto.getThirdTerm().getEndDate() != null) {
      studentClassRepository.updateClassesEndDateBySessionAndTermAndSchoolCode(updateTermDto.getSession(), 3,
          updateTermDto.getThirdTerm().getEndDate(), SchoolContext.getSchoolCode());

    }
    logger.info("/{updateTermDto Updated}");
  }

  @Override
  public SessionDto getSession(String session) {
    List<StudentClass> classes = studentClassRepository.findBySessionAndSchoolCode(session,
        SchoolContext.getSchoolCode());
    StudentClass first = classes.stream().filter(clas -> clas.getTerm().equals(1)).findFirst()
        .orElseThrow(() -> new UserNotFoundException("Class Not found"));
    StudentClass second = classes.stream().filter(clas -> clas.getTerm().equals(2)).findFirst()
        .orElseThrow(() -> new UserNotFoundException("Class Not found"));
    StudentClass third = classes.stream().filter(clas -> clas.getTerm().equals(3)).findFirst()
        .orElseThrow(() -> new UserNotFoundException("Class Not found"));
    SessionDto sessionDto = new SessionDto(session,
        new TermDto(first.getTerm(), first.getStartDate(), first.getEndDate()),
        new TermDto(second.getTerm(), second.getStartDate(), second.getEndDate()),
        new TermDto(third.getTerm(), third.getStartDate(), third.getEndDate()));
    return sessionDto;
  }

  @Override
  public RegisterSchoolDto SchoolSetUp(RegisterSchoolDto schoolDto) {

    if (schoolDto.getName() == null || schoolDto.getName().isBlank() || schoolDto.getName().isEmpty()) {
      throw new EmptyFieldException("School Name Cannot be Empty");
    }
    if (schoolDto.getAddress() == null || schoolDto.getAddress().isBlank() || schoolDto.getAddress().isEmpty()) {
      throw new EmptyFieldException("School Address is Required");
    }
    if (schoolDto.getLocalGovernment() == null || schoolDto.getLocalGovernment().isBlank()
        || schoolDto.getLocalGovernment().isEmpty()) {
      throw new EmptyFieldException("School LGA is Required");
    }
    if (schoolDto.getState() == null || schoolDto.getState().isBlank()
        || schoolDto.getState().isEmpty()) {
      throw new EmptyFieldException("School State is required");

    }
    if (schoolDto.getCity() == null || schoolDto.getCity().isBlank()
        || schoolDto.getCity().isEmpty()) {
      throw new EmptyFieldException("City is required");

    }
    final SchoolDetails school = modelMapper.map(schoolDto, SchoolDetails.class);
    String schoolCode;
    do {
      schoolCode = generateSchoolCode(schoolDto.getName());
    } while (schoolRepository.existsByCode(schoolCode));

    school.setCode(schoolCode);

    final SchoolDetails savedSchool = schoolRepository.save(school);
    return modelMapper.map(savedSchool, RegisterSchoolDto.class);

  }

  private String generateSchoolCode(String schoolName) {
    // Get first 3 letters of the school name (uppercase)
    String prefix = schoolName.substring(0, 3).toUpperCase();

    // Generate a hash from the school name (convert negative to positive)
    int hash = Math.abs(schoolName.hashCode());

    // Extract 2 digits from the hash (ensures same name → same hash part)
    String hashPart = String.valueOf(hash).substring(0, 2);

    // Generate a UUID and extract 1 alphanumeric character (ensures uniqueness)
    String uniquePart = UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "").substring(0, 2);

    // Combine prefix, hash, and unique UUID part (total 6 characters)
    return prefix + hashPart + uniquePart;

  }

  @Override
  public FeesDto createFee(FeesDto fee) {

    FeesStructureJ newFee = modelMapper.map(fee, FeesStructureJ.class);
    newFee.setSchoolCode(SchoolContext.getSchoolCode());

    return modelMapper.map(feesRepo.save(newFee), FeesDto.class);
  }

  @Override
  public List<FeesDto> fetchAllFees(String session) {
    if (session.isEmpty() || session.isBlank()) {
      throw new EmptyFieldException("Session name cannot be null");
    }
    List<FeesStructureJ> fees = feesRepo.findBySessionNameAndSchoolCode(session, SchoolContext.getSchoolCode());
    Type listType = new TypeToken<List<FeesDto>>() {
    }.getType();
    return modelMapper.map(fees, listType);
  }

  @Override
  public List<FeesDto> fetchAllFees(String session, int term) {
    if (session.isEmpty() || session.isBlank()) {
      throw new EmptyFieldException("Session name cannot be null");
    }
    if (term < 0 || term > 3) {
      throw new EmptyFieldException("Invalid Term Input");
    }
    List<FeesStructureJ> fees = feesRepo.findBySessionNameAndTermAndSchoolCode(session, term,
        SchoolContext.getSchoolCode());
    Type listType = new TypeToken<List<FeesDto>>() {
    }.getType();
    return modelMapper.map(fees, listType);
  }

  @Override
  public FeesDto editFee(FeesDto fee) {

    fee.setSchoolCode(SchoolContext.getSchoolCode());
    FeesStructureJ data = modelMapper.map(fee, FeesStructureJ.class);
    // feesRepo.updateFeeByIdAndSchoolCode(data.getId(),
    // SchoolContext.getSchoolCode(), data);
    int a = feesRepo.updateCompleteFeesStructure(data.getId(), SchoolContext.getSchoolCode(), data.getTerm(),
        data.getFeeName(),
        data.getAmount(), data.getApplicability(), data.getAvailabilityStatus(), data.getClassesNames());
    System.out.println("This is a:" + a);
    return fee;
  }

  @Override
  public void startTerm(String session, int term) {
    if (session.isEmpty() || session.isBlank()) {
      throw new EmptyFieldException("Invalid Session Name");
    }
    if (term < 1 || term > 3) {
      throw new EmptyFieldException("Invalid term");
    }
    if (termdata.existsBySessionNameAndTermAndSchoolCode(session, term, SchoolContext.getSchoolCode())) {
      throw new ConflictException("term Data already exists");
    }
    List<StudentJ> students = studentsRepo.findByStatusAndSchoolCode(StudentStatus.active,
        SchoolContext.getSchoolCode());
    List<StudentTermDataJ> studdentTerm = students.stream().map(student -> {

      StudentTermDataJ previoustTerm = termdata.findByStudentIdAndSessionNameAndTermAndSchoolCode(student.getId(),
          session, term, SchoolContext.getSchoolCode());
      // if (previoustTerm== null) {
      // return new StudentTermDataJ()
      // }

      StudentClass previousClass = studentClassRepository.findById(previoustTerm.getClassId()).orElseThrow();

      final List<FeesStructureJ> previousClassFees = feesRepo
          .findBySessionNameAndTermAndSchoolCodeAndClassesName(
              previoustTerm.getSessionName(),
              previoustTerm.getTerm(),
              SchoolContext.getSchoolCode(), previousClass.getClassName());

      int previousFeeAmount = previousClassFees.stream()
          .filter(s -> s.getApplicability().equals(Applicability.required)).mapToInt(FeesStructureJ::getAmount).sum();

      StudentTermDataJ currentTermData = new StudentTermDataJ(SchoolContext.getSchoolCode(), student.getId(),

          previoustTerm.getClassId(), session, term, 0,
          (previoustTerm.getLastTermDebt() + previousFeeAmount) - previoustTerm.getAmountPaid(), 0);
      return currentTermData;
    }).collect(Collectors.toList());

    termdata.saveAll(studdentTerm);

  }

}
