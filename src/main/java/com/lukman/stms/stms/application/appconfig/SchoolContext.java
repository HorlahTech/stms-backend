package com.lukman.stms.stms.application.appconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lukman.stms.stms.infrastructure.exception.UserNotFoundException;
import com.lukman.stms.stms.infrastructure.repository.SchoolDetailsRepository;

import jakarta.annotation.PostConstruct;

@Component
public class SchoolContext {
    private static final ThreadLocal<String> CURRENT_SCHOOL = new ThreadLocal<>();

    private static SchoolDetailsRepository staticSchoolRepository;
    @Autowired
    private SchoolDetailsRepository schoolRepository;

    @PostConstruct
    public void init() {
        staticSchoolRepository = schoolRepository;
    }

    public static void setSchoolCode(String schoolId) {

        if (staticSchoolRepository.existsByCode(schoolId)) {
            CURRENT_SCHOOL.set(schoolId);
        } else {
            throw new UserNotFoundException("School with the code " + schoolId + " does not exist");
        }

    }

    public static String getSchoolCode() {
        return CURRENT_SCHOOL.get();
    }

    public static void clearSchoolCode() {
        CURRENT_SCHOOL.remove();
    }
}
