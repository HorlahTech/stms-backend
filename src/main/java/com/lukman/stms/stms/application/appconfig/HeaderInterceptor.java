package com.lukman.stms.stms.application.appconfig;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lukman.stms.stms.infrastructure.exception.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HeaderInterceptor implements HandlerInterceptor {
    private static final String SCHOOLCODE_HEADER = "X-School-Code";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String schoolCode = request.getHeader(SCHOOLCODE_HEADER);
        if (schoolCode == null || schoolCode.isEmpty()) {
            // response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            // throw new ForbiddenException("School Code is Required");
        } else {
            SchoolContext.setSchoolCode(schoolCode);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        SchoolContext.clearSchoolCode();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
