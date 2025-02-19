package com.lukman.stms.stms.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lukman.stms.stms.models.StudentJ;
import java.util.List;
import com.lukman.stms.stms.application.constant.StudentStatus;

@Repository
public interface StudentRepository extends MongoRepository<StudentJ, String> {
    List<StudentJ> findByStatus(StudentStatus status);
}
