package com.lukman.stms.stms.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lukman.stms.stms.models.SchoolDetails;

public interface SchoolDetailsRepository extends MongoRepository<SchoolDetails, String> {
    boolean existsByCode(String code);

}
