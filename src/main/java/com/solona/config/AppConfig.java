package com.solona.config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class  AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(management ->
                        management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/**").authenticated()
                                .requestMatchers("/api/products/*/reviews").permitAll()
                                .anyRequest().permitAll()
                ).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class
                ).csrf(csrf->csrf.disable())
                        .cors(cors ->cors.configurationSource(corsConfigrationSource()));// <-- Adjust this as per your security policy

        return http.build();
    }

    private CorsConfigurationSource corsConfigrationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg =new CorsConfiguration();
                                                    //changed from localhost:3000 to http://* for testing purpose
                cfg.setAllowedOriginPatterns(Arrays.asList(
                        "http://localhost:3000",
                        "http://192.168.*:*"
                ));
                //cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                cfg.setAllowCredentials(true);
                cfg.setExposedHeaders(Collections.singletonList("Authorization"));
                cfg.setMaxAge(3600L);
                return cfg;
            }
        };
//        return null;
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
