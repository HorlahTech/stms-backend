package com.lukman.stms.stms.infrastructure.api;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lukman.stms.stms.application.constant.ClassEnum;
import com.lukman.stms.stms.application.dto.request.StudentClassDto;
import com.lukman.stms.stms.application.dto.request.UpdateTermDto;
import com.lukman.stms.stms.infrastructure.exception.ConflictException;
import com.lukman.stms.stms.infrastructure.exception.EmptyFieldException;
import com.lukman.stms.stms.infrastructure.repository.StudentClassRepository;
import com.lukman.stms.stms.models.FeesStructureJ;
import com.lukman.stms.stms.models.StudentClass;
import com.lukman.stms.stms.service.StudentClassService;

@Service
public class StudentClassImpl implements StudentClassService {
  @Autowired
  private StudentClassRepository studentClassRepository;
  @Autowired
  ModelMapper modelMapper;
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public void sessionSetUp(StudentClassDto session) {
    if (session.getSession() == null || session.getSession().isBlank() || session.getSession().isEmpty()) {
      throw new EmptyFieldException("Session Name Cannot be null");
    }
    if (studentClassRepository.existsBySession(session.getSession())) {
      throw new ConflictException("Session Already Exist");
    }
    List<StudentClass> classList = new ArrayList<>();
    for (int i = 1; i <= 3; i++) {
      for (int j = 0; j < ClassEnum.values().length; j++) {

        StudentClassDto studentClassDto = new StudentClassDto(ClassEnum.values()[j], i, session.getSession());

        classList.add(modelMapper.map(studentClassDto, StudentClass.class));
      }
    }

    studentClassRepository.saveAll(classList);
  }

  @Override
  public List<StudentClassDto> getallSessionClass(String session) {
    if (session == null || session.isBlank() || session.isEmpty()) {
      throw new EmptyFieldException("Session Name Cannot be null");
    }
    final List<StudentClass> response = studentClassRepository.findBySession(session);

    java.lang.reflect.Type listType = new TypeToken<List<StudentClassDto>>() {
    }.getType();
    List<StudentClassDto> entityToDto = modelMapper.map(response, listType);
    return entityToDto;
    // return modelMapper.map(response, StudentClassDto.class);
  }

  @Override
  public void updateTermDate(UpdateTermDto updateTermDto) {
    if (updateTermDto.getSession() == null || updateTermDto.getSession().isBlank()
        || updateTermDto.getSession().isEmpty()) {
      throw new EmptyFieldException("Session can not be Empty");
    }

    // Update First term start Date
    if (updateTermDto.getFirstTerm() != null && updateTermDto.getFirstTerm().getStartDate() != null) {
      studentClassRepository.updateClassesStartDateBySessionAndTerm(updateTermDto.getSession(), 1,
          updateTermDto.getFirstTerm().getStartDate());

    }
    // Update First term end Date
    if (updateTermDto.getFirstTerm() != null && updateTermDto.getFirstTerm().getEndDate() != null) {
      studentClassRepository.updateClassesEndDateBySessionAndTerm(updateTermDto.getSession(), 1,
          updateTermDto.getFirstTerm().getEndDate());

    }
    // Update Second term start Date
    if (updateTermDto.getSecondTerm() != null && updateTermDto.getSecondTerm().getStartDate() != null) {
      studentClassRepository.updateClassesStartDateBySessionAndTerm(updateTermDto.getSession(), 2,
          updateTermDto.getSecondTerm().getStartDate());

    }
    // Update Second term end Date
    if (updateTermDto.getSecondTerm() != null && updateTermDto.getSecondTerm().getEndDate() != null) {
      studentClassRepository.updateClassesEndDateBySessionAndTerm(updateTermDto.getSession(), 2,
          updateTermDto.getSecondTerm().getEndDate());

    }
    // Update Third term start Date
    if (updateTermDto.getThirdTerm() != null && updateTermDto.getThirdTerm().getStartDate() != null) {
      studentClassRepository.updateClassesStartDateBySessionAndTerm(updateTermDto.getSession(), 3,
          updateTermDto.getThirdTerm().getStartDate());

    }
    // Update Third term end Date
    if (updateTermDto.getThirdTerm() != null && updateTermDto.getThirdTerm().getEndDate() != null) {
      studentClassRepository.updateClassesEndDateBySessionAndTerm(updateTermDto.getSession(), 3,
          updateTermDto.getThirdTerm().getEndDate());

    }
    logger.info("/{updateTermDto Updated}");
  }

  @Override
  public List<FeesStructureJ> fetchFees() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'fetchFees'");
  }

}
