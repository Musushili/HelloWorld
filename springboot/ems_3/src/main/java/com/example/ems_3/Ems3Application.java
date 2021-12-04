package com.example.ems_3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.example.ems_3.mapper")
public class Ems3Application {

    public static void main(String[] args) {
        SpringApplication.run(Ems3Application.class, args);
    }

}
