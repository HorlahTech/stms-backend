package com.lukman.stms.stms.infrastructure.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lukman.stms.stms.application.constant.StudentStatus;
import com.lukman.stms.stms.models.StudentJ;

@Repository
public interface StudentRepository extends MongoRepository<StudentJ, String> {
    List<StudentJ> findByStatusAndSchoolCode(StudentStatus status, String schoolCode);

    @Query(value = "{'surName':?0, 'firstName':?1,'middleName':?2, 'homeAddress':?3, 'schoolCode':?4}", exists = true)
    boolean existsBySurNameAndFirstNameAndMiddleNameAndHomeAddressAndSchoolCode(String surname, String firstName,
            String middleName, String homeAddress, String schoolCode);
}
