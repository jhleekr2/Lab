package com.example.spring_study1.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
//Spring Security 기능을 사용하려면 이 어노테이션을 써줘야 한다
public class SecurityConfig {


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//이런 메소드는 언제 쓰이는지 쓰임새를 아는 것이 중요!
		//스프링 시큐리티 기능을 사용하고자 할 때 이 메소드 안에 작성
		http
		.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		//csrf해킹 방어를 위한 보호 방법. => 나중에 따로 HTML, JSP와 자바스크립트에도(프론트영역->백엔드영역으로 데이터가 넘어갈때) csrf 방어기능을 추가로 넣어야 한다.
		.cors(cors -> cors.configurationSource(CorsConfigurationSource()))
		//cors - 특정 서버로만 데이터를 넘길 수 있도록 설정
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
		//세션 설정
		.authorizeHttpRequests(authz->authz.requestMatchers("/", "/loginPage", "/logout", "/noticeCheckPage", "/registerPage", "/menu/all")		
			.permitAll()
			.requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
			.requestMatchers("/resources/**","/WEB-INF/**").permitAll()
			.requestMatchers("/noticeAddPage", "/noticeModifyPage").hasAnyAuthority("ADMIN", "MANAGER")
				.requestMatchers(HttpMethod.POST, "/menu/add").hasAnyAuthority("ADMIN", "MANAGER")
				.requestMatchers(HttpMethod.POST, "/menu/update").hasAnyAuthority("ADMIN", "MANAGER")
				.requestMatchers(HttpMethod.DELETE, "/menu/delete").hasAnyAuthority("ADMIN", "MANAGER")
				.anyRequest().authenticated() //로그인을 해야지만 접근이 가능하게끔 만듬. 그렇기 때문에 로그인 페이지로 자동 이동
				)



		.formLogin(
				login->login.loginPage("/loginPage") //url을 작성해서 로그인페이지로 이동할때
				.loginProcessingUrl("/login")
				.failureUrl("/loginPage?error=true")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(authenticationSuccessHandler()) //로그인 성공 후 어느 페이지로 이동하는지를 안내하며 동시에 세션과 연관
				.permitAll()
				)
		
		.logout(logout->logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))// logout URL을 통해서 로그아웃이 진행됨
				.logoutSuccessUrl("/")//로그아웃 성공 후 이 URL로 리다이렉팅
				.invalidateHttpSession(true)//세션무효화 => 세션공간안에 있던 데이터 사라짐
				.deleteCookies("JSESSIONID")//쿠키삭제
				.permitAll()
				);
		
		return http.build();
		//최종 http에 적용시킬때 사용하는 메소드
	}

	//successHandler를 따로 작성해 주어야만 한다.
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new SimpleUrlAuthenticationSuccessHandler() {
			
			//이런건 기능을 암기하기보다는 개발할때 그날그날 블로그 보고 따라 쓰는 것이다.
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				// 로그인이 성공했을 때 우리가 특별기능을 넣고 싶을 때(세션과 권한 기능)
				HttpSession session = request.getSession(); // 세션 기능을 가져 온것
				boolean isManager = authentication.getAuthorities().stream()
						.anyMatch(grantedAuthority -> 
						grantedAuthority.getAuthority().equals("ADMIN") ||
						grantedAuthority.getAuthority().equals("MANAGER"));
				if(isManager) {
					session.setAttribute("MANAGER", true);
				}
				session.setAttribute("username", authentication.getName());
				// 로그인할때 form에서 전달받은 사용자 이름을 다시 세션에 집어넣는다.
				//세션에다가 로그인한 아이디를 저장한다.
				session.setAttribute("isAuthenticated", true);
				//세션에다가 로그인 여부를 저장
				// request.getContextPath() -> localhost:8080
				response.sendRedirect(request.getContextPath()+"/");
				super.onAuthenticationSuccess(request, response, authentication);
			}
			
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		//DB에 패스워드를 암호화해서 저장
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public CorsConfigurationSource CorsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost:8080"));
		//localhost:8080서버에서는 프론트에서 백엔드단 혹은 백엔드단에서 프론트단으로 데이터를 주고받을수 있게 만든것
		//일반적으로는 프론트단 localhost:3000 백엔드단 localhost:8080
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}


}
