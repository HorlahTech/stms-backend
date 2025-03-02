package com.lukman.stms.stms.infrastructure.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lukman.stms.stms.models.FeesStructureJ;

@Repository
public interface FeesStructureRepository extends MongoRepository<FeesStructureJ, String> {
    @Query("{'classesNames':?0,'sessionName':?1, 'term':?2, 'schoolCode':?3}")
    List<FeesStructureJ> findByClassesNamesAndSessionNameAndTermAndSchoolCode(List<String> classesNames,
            String sessionName,
            int term, String schoolCode);

    @Query("{'sessionName':?0, 'term':?1, 'schoolCode':?2}")
    List<FeesStructureJ> findBySessionNameAndTermAndSchoolCode(
            String sessionName,
            int term, String schoolCode);

    @Query("{'sessionName':?0, 'schoolCode':?1}")
    List<FeesStructureJ> findBySessionNameAndSchoolCode(
            String sessionName,
            String schoolCode);

}
