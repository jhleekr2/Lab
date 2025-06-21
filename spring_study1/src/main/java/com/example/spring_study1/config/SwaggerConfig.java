package com.example.spring_study1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
//Configuration 어노테이션은 특이하게 일반 서비스나 컨트롤러와 달리
//Bean으로 등록해야지만 그때부터 스프링 컨테이너에 등록된다.
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("Cafe API Documentation")
				.version("1.0")
				.description("카페 프로젝트 API 명세서")
				); 
	}

}
