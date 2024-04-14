package com.example.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DbConfig {

    @Autowired
    private Environment env;

    public void printDatabaseConfig() {
        String url = env.getProperty("spring.datasource.url");
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");

        System.out.println("Database URL: " + url);
        System.out.println("Database Username: " + username);
        System.out.println("Database Password: " + password);
    }
}