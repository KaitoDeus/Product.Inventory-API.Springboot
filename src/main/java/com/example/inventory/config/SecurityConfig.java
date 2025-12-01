package com.example.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenUtil);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF vì dùng JWT
                .csrf(csrf -> csrf.disable())

                // STATELESS: Server không lưu trạng thái đăng nhập (Session)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Cấu hình quyền truy cập
                .authorizeHttpRequests(auth -> auth
                        // Cho phép public GET /api/products/**
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()

                        // POST, PUT chỉ cho ADMIN và MANAGER
                        .requestMatchers(HttpMethod.POST, "/api/products/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").hasAnyRole("ADMIN", "MANAGER")

                        // DELETE chỉ cho ADMIN
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")

                        // Các endpoint public khác
                        .requestMatchers("/api/auth/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-resources/**")
                        .permitAll()

                        // Tất cả các request còn lại yêu cầu xác thực
                        .anyRequest().authenticated())

                // Dùng JWT thay thế cho login bằng username - password
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

                // Trả lỗi JSON cho 401/403 theo GlobalExceptionHandler
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(globalExceptionHandler)
                        .accessDeniedHandler(globalExceptionHandler))

                // Cho phép dùng httpBasic nếu cần test nhanh
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
