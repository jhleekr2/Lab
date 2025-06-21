package com.example.spring_study1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication 어노테이션 안에는 @ComponentScan 어노테이션이 내부적으로 포함
@SpringBootApplication
public class SpringStudy1Application {

	//프로그램이 여기서 시작
	public static void main(String[] args) {
		SpringApplication.run(SpringStudy1Application.class, args);
	}

}
