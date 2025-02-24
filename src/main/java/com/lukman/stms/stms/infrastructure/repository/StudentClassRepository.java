package com.lukman.stms.stms.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lukman.stms.stms.models.StudentClass;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentClassRepository extends MongoRepository<StudentClass, String> {
    Optional<StudentClass> findByClassNameAndTermAndSession(String name, int term, String session);

    List<StudentClass> findBySession(String session);

    List<StudentClass> findBySessionAndTerm(String session, int term);

    Boolean existsBySession(String session);

    @Transactional
    @Query("{'session':?0, 'term':?1}")
    @Update("{'$set':{'startDate': ?2}}")
    void updateClassesStartDateBySessionAndTerm(String session, int term, LocalDateTime startDate);

    @Transactional
    @Query("{'session':?0, 'term':?1}")
    @Update("{'$set':{'endDate': ?2}}")
    void updateClassesEndDateBySessionAndTerm(String session, int term, LocalDateTime startDate);

}
