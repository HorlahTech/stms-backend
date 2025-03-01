package com.lukman.stms.stms.infrastructure.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lukman.stms.stms.application.constant.StudentStatus;
import com.lukman.stms.stms.models.StudentJ;

@Repository
public interface StudentRepository extends MongoRepository<StudentJ, String> {
    List<StudentJ> findByStatusAndSchoolCode(StudentStatus status, String schoolCode);
}
