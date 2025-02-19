package com.lukman.stms.stms.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lukman.stms.stms.models.StudentClass;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentClassRepository extends MongoRepository<StudentClass, String> {
    Optional<StudentClass> findByNameAndTermAndSession(String name, int term, String session);

    List<StudentClass> findBySession(String session);

    Boolean existsBySession(String session);

    @Transactional
    @Query("{'session':?0, 'term':?1}")
    @Update("{'$set':{'startDate': ?2}}")
    void updateClassesStartDateBySessionAndTerm(String session, int term, LocalDate startDate);

    @Transactional
    @Query("{'session':?0, 'term':?1}")
    @Update("{'$set':{'endDate': ?2}}")
    void updateClassesEndDateBySessionAndTerm(String session, int term, LocalDate startDate);

}
