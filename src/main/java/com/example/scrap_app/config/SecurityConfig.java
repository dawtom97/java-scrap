package com.example.scrap_app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests
                            (
                                    auth -> auth
                                            .requestMatchers("/","/login","/create-user").permitAll()
                                            .anyRequest().authenticated()
                            )
                    .sessionManagement
                            (
                                    session -> session
                                            .maximumSessions(1).maxSessionsPreventsLogin(false)
                            )
                    .formLogin
                            (
                                    login -> login
                                            .loginPage("/login")
                                            .defaultSuccessUrl("/dashboard",true)
                                            .permitAll()
                            )
                    .logout
                            (
                            logout -> logout
                                    .logoutUrl("/logout")
                                    .logoutSuccessUrl("/login")
                                    .invalidateHttpSession(true)
                                    .deleteCookies("JSESSIONID")
                                    .permitAll()
                            );
            return http.build();
    }
}
