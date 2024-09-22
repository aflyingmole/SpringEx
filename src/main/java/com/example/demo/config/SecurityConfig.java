package com.example.demo.config;

import com.example.demo.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Autowired CustomUserDetailsService detailsService;
	@Bean   //PasswordEncoder객체를 생성해 스프링빈으로 등록하기
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		.csrf(csrfConfigure->csrfConfigure.disable()) //csrf토큰 사용하지 않기
        .authorizeHttpRequests(authz -> authz
	        .requestMatchers("/member/**").authenticated() //인증된 사용자만 접근 허용
	       // .requestMatchers("/admin/**").authenticated()
	        .requestMatchers("/admin/**").hasAuthority("ADMIN")//ADMIN 이라는 권한이 있는지 검사
	        .anyRequest().permitAll() // 나머지 요청은 인증 없이 모두 접근가능
        )
        .formLogin(formLogin -> formLogin
            .loginPage("/login") //login page 
            .loginProcessingUrl("/loginOk") //login 페이지의 action속성
            .defaultSuccessUrl("/", true) 
            .permitAll()
        )
        .logout((logoutConfig) ->
             logoutConfig.logoutSuccessUrl("/") 
         )
       .userDetailsService(detailsService);
		return http.build();
	}
}



















