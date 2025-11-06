package com.manalese.facebook;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Configure CORS to allow a frontend application to access the API.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Apply CORS to all endpoints under /api
                .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000","http://localhost:8080" ,"http://localhost:5173/" , "https://post-api-tagm.onrender.com") // Replace with your frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}