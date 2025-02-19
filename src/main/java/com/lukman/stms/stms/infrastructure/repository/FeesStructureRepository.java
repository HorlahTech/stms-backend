package com.lukman.stms.stms.infrastructure.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.lukman.stms.stms.models.FeesStructureJ;

public interface FeesStructureRepository extends MongoRepository<FeesStructureJ, String> {
    @Query("{'classesNames':?0,'sessionName':?1, 'term':?2}")
    List<FeesStructureJ> findByClassesNamesAndSessionNameAndTerm(List<String> classesNames, String sessionName,
            int term);

}
