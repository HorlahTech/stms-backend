package com.lukman.stms.stms.application.appconfig;

import org.springframework.beans.factory.annotation.Autowired;

import com.lukman.stms.stms.infrastructure.exception.UserNotFoundException;
import com.lukman.stms.stms.infrastructure.repository.SchoolDetailsRepository;

public class SchoolContext {
    private static final ThreadLocal<String> CURRENT_SCHOOL = new ThreadLocal<>();
    @Autowired
    private static SchoolDetailsRepository schoolRepository;

    public static void setSchoolCode(String schoolId) {

        if (schoolRepository.existsByCode(schoolId)) {
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
