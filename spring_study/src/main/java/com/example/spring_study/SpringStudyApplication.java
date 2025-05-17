package com.example.spring_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.spring_study", "com.example.news"}) // 스캔할 패키지들을 배열로 지정
// 또는 더 간결하게 두 패키지의 공통 부모 패키지를 스캔하도록 지정할 수도 있습니다.
// @ComponentScan("com.example")

// 책에서는 스프링 부트의 진입점을 두 개로 만들어서 구성해 두었는데, 이렇게 했더니 빌드가 실패하는 것은 물론
// 인텔리제이 환경에서 다양한 에러가 발생하므로 그렇게 하지 않는다.
// 물론 이클립스에서는 정상 작동함
public class SpringStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStudyApplication.class, args);
    }

}
