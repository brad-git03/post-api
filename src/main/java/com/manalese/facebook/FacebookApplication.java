package com.manalese.facebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacebookApplication {

    public static void main(String[] args) {
        // This line runs the Spring Boot application, starting the embedded web server (Tomcat)
        // and setting up the beans (Controller, Repository, etc.).
        SpringApplication.run(FacebookApplication.class, args);
    }
}