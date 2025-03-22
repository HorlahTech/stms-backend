package com.lukman.stms.stms.infrastructure.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lukman.stms.stms.application.constant.Applicability;
import com.lukman.stms.stms.application.constant.AvailabilityEnum;
import com.lukman.stms.stms.models.FeesStructureJ;

@Repository
public interface FeesStructureRepository extends MongoRepository<FeesStructureJ, String> {
        @Query("{'classesNames':?3,'sessionName':?0, 'term':?1, 'schoolCode':?2}")
        List<FeesStructureJ> findBySessionNameAndTermAndSchoolCodeAndClassesName(
                        String sessionName,
                        int term, String schoolCode, String... classesNames);

        @Query("{'sessionName':?0, 'term':?1, 'schoolCode':?2}")
        List<FeesStructureJ> findBySessionNameAndTermAndSchoolCode(
                        String sessionName,
                        int term, String schoolCode);

        @Query("{'sessionName':?0, 'schoolCode':?1}")
        List<FeesStructureJ> findBySessionNameAndSchoolCode(
                        String sessionName,
                        String schoolCode);

        // @Query("{'id':?0, 'schoolCode':?1}")
        // @Update("{'$set':{'fee':?2}}")
        // @Transactional
        // int updateFeeByIdAndSchoolCode(String id, String schoolCode, FeesStructureJ
        // fee);
        @Query("{'id':?0, 'schoolCode':?1}")
        @Update("{'$set':{'fee':?2}}")
        @Transactional
        int updateFeeByIdAndSchoolCode(String id, String schoolCode, FeesStructureJ fee);

        @Query("{'id':?0, 'schoolCode':?1}")
        @Update("{'$set':{'term':?2, 'feeName':?3, 'amount':?4, 'applicability':?5, 'availabilityStatus':?6, 'classesNames':?7}}")
        @Transactional
        int updateCompleteFeesStructure(String id, String schoolCode,
                        int term, String feeName,
                        int amount, Applicability applicability,
                        AvailabilityEnum availabilityStatus,
                        List<String> classesNames);
}
