package com.example.spbt1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("Controller") // HomeController.java 파일이 있는 패키지 명
@ComponentScan("Service")
@ComponentScan("Config")
public class Spbt1Application {

    public static void main(String[] args) {
        SpringApplication.run(Spbt1Application.class, args);
    }

}
