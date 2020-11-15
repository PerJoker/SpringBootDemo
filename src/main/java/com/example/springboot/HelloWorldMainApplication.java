package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 *
 */
@SpringBootApplication
@ServletComponentScan
public class HelloWorldMainApplication {
    public static void main(String[] args) {
        // Spring应用启动
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
