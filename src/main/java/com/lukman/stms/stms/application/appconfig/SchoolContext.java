package com.lukman.stms.stms.application.appconfig;

public class SchoolContext {
    private static final ThreadLocal<String> CURRENT_SCHOOL = new ThreadLocal<>();

    public static void setSchoolCode(String schoolId) {
        CURRENT_SCHOOL.set(schoolId);
    }

    public static String getSchoolCode() {
        return CURRENT_SCHOOL.get();
    }

    public static void clearSchoolCode() {
        CURRENT_SCHOOL.remove();
    }
}
