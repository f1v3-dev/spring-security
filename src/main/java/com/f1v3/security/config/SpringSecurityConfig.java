package com.f1v3.security.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화 ->
                .cors(AbstractHttpConfigurer::disable) // cors 비활성화
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() // forward 요청은 모든 사용자에게 허용 ?
                        .requestMatchers("/images/**", "/view/join", "/auth/login").permitAll()
                        .anyRequest().authenticated() // 모든 요청에 대해 인증을 요구
                )
                .formLogin(login -> login  // form 로그인 설정
                        .loginPage("/view/login") // thymeleaf view 경로
                        .loginProcessingUrl("/login-process") // @PostMapping("/login-process") -> 로그인 처리 Controller
                        .usernameParameter("userId") // Thymeleaf 내의 userId name -> 회원의 아이디
                        .passwordParameter("password") // Thymeleaf 내의 password name -> 회원의 비밀번호
                        .defaultSuccessUrl("/view/dashboard", true) // 로그인 성공 시 이동할 URL
                        .permitAll() // 로그인 페이지는 모든 사용자에게 허용!
                )
                .logout(Customizer.withDefaults()); // 로그아웃은 기본 설정 (/logout -> 인증 해제)

        return http.build();
    }
}