package com.lukman.stms.stms.application.appconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:*", "http://127.0.0.1:*", "http://10.0.2.2:*")
                .allowedMethods("GET",
                        "POST", "PACTH", "DELETE", "PUT");
    }

}
