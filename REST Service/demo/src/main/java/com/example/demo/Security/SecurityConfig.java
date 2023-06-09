/*package com.example.demo.Security;

import com.example.demo.Services.UserDetailsCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsCustomImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.headers((headers) -> {
            headers.frameOptions((frameOption) -> frameOption.disable());
        });
        http

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest()
                        .permitAll());
        return http.build();
    }

    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    public void configureIgnores(WebSecurity web) {
        web
                .ignoring()
                .requestMatchers("/h2-console/**");
    }
}
*/