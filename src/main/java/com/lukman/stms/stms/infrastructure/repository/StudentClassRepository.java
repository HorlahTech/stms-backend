package com.lukman.stms.stms.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lukman.stms.stms.models.StudentClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentClassRepository extends MongoRepository<StudentClass, String> {
    @Query("{'className':?0, 'term':?1, 'session':?2, 'schoolCode':?3}")
    Optional<StudentClass> findByClassNameAndTermAndSessionAndSchoolCode(String className, int term, String session,
            String schoolCode);

    @Query("{'session':?0, 'schoolCode'?1}")
    List<StudentClass> findBySessionAndSchoolCode(String session, String schoolCode);

    @Query("{'session':?0, 'term':?1, 'schoolCode':?2}")
    List<StudentClass> findBySessionAndTermAndSchoolCode(String session, int term, String schoolCodeString);

    @Query("{'session':?0,  'schoolCode':?1}")
    Boolean existsBySessionAndSchoolCode(String session, String schoolCode);

    @Transactional
    @Query("{'session':?0, 'term':?1, 'schoolCode':?3}")
    @Update("{'$set':{'startDate': ?2}}")
    void updateClassesStartDateBySessionAndTermAndSchoolCode(String session, int term, LocalDateTime startDate,
            String schoolCode);

    @Transactional
    @Query("{'session':?0, 'term':?1, 'schoolCode':?3}")
    @Update("{'$set':{'endDate': ?2}}")
    void updateClassesEndDateBySessionAndTermAndSchoolCode(String session, int term, LocalDateTime startDate,
            String schoolCode);

}
