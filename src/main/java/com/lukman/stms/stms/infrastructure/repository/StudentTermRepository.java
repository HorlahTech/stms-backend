package com.lukman.stms.stms.infrastructure.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lukman.stms.stms.models.StudentTermDataJ;

@Repository
public interface StudentTermRepository extends MongoRepository<StudentTermDataJ, String> {
    List<StudentTermDataJ> findByStudentIdAndSchoolCode(String studentId, String schoolCode);

    List<StudentTermDataJ> findByClassIdAndSchoolCode(String classId, String schoolCode);

}
