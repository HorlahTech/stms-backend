package com.lukman.stms.stms.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lukman.stms.stms.models.SchoolDetails;

@Repository
public interface SchoolDetailsRepository extends MongoRepository<SchoolDetails, String> {
    boolean existsByCode(String code);

    SchoolDetails findByCode(String code);

}
