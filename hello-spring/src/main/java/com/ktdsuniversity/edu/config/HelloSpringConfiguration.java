package com.ktdsuniversity.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// application.yml에서 작성할 수 없는 설정들을 적용하기 위한 Annotation
// @Component의 자식 Annotation
@Configuration
// spring-boot-starter-validation 동작 활성화 시키기
// @EnableWebMvc가 추가되면 application.yml의 mvc 관련 설정들이 모두 무시된다.
//   1. spring.mvc.view.prefix, spring.mvc.view.suffix
//   2. src/main/resources/static 경로 사용 불가능.
@EnableWebMvc
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class HelloSpringConfiguration implements 
	// WebMvc 설정을 위한 Configuration
	// @EnableWebMvc Annotation 에서 적용하는 기본 설정들을 변경하기 위함
	WebMvcConfigurer {
	
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    
    @Bean
    SecurityFilterChain configureFilterChain(HttpSecurity httpSecurity) {
        
        // httpSecurity.csrf(csrf -> csrf.disable());
        
        // UsernamePasswordAuthenticationFilter 수정
        httpSecurity.formLogin(formLogin -> formLogin
                                            // 로그인 URL을 지정. 로그인 시 이동할 페이지 지정
                                            .loginPage("/login")
                                            // Login 인증 처리 URL 지정 (UsernameAndPasswordAuthenticationProvider가 실행될 Endpoint)
                                            .loginProcessingUrl("/login-provider")
                                            // 로그인에 필요한 아이디 파라미터 이름을 username에서 email로 변경
                                            .usernameParameter("email")
                                            // 로그인 성공 처리 handler
                                            .successHandler(successHandler)
                                            // 로그인에 실패 처리 handler
                                            .failureHandler(failureHandler));
        
        return httpSecurity.build();
    }
    
	// configureViewResolvers 설정
	// spring.mvc.view.prefix, spring.mvc.view.suffix 재설
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	// addResourceHandlers
	// src/main/resources/static 경로의 endpoint 재설정
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// /static/css/ 폴더에 있는 파일들에 대한 Endpoint 설정
		registry.addResourceHandler("/css/**") // static/css 의 endpoint
				.addResourceLocations("classpath:/static/css/");// static/css의 물리적인 위치
		
		// static/image/ 폴더에 있는 파일들에 대한 Endpoint 설정
		registry.addResourceHandler("/image/**") 
		.addResourceLocations("classpath:/static/image/");
				
		// static/js/ 폴더에 있는 파일들에 대한 Endpoint 설정
		registry.addResourceHandler("/js/**") 
		.addResourceLocations("classpath:/static/js/");
	}

}
