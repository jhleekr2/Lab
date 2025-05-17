package Config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * application.properties에 등록해둔 것 가져오기
     */
    @Value("${spring.mvc.view.prefix}")
    private String prefix;

    @Value("${spring.mvc.view.suffix}")
    private String suffix;

    @PostConstruct
    public void init() {
        System.out.println("View Prefix: " + prefix); // 추가
        System.out.println("View Suffix: " + suffix); // 추가
    }

    /**
     * ViewResolver를 Bean 객체로 등록
     * prefix, suffix (Property 설정)은 application.properties에 등록해둔 것을 가져와서 쓸 것임
     * @return
     */
    @Bean
    public ViewResolver internalResoruceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(prefix);
        resolver.setSuffix(suffix);
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(0); // 뷰 리졸버의 우선순위를 가장 높게 설정
        return resolver;
    }

    /**
     * 정적 리소스 등록
     * swagger 등록
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //넣지 않았을 때와 달리 넣으면 이미지 파일을 읽을 수 있다고 한다.
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static");

    }
}
