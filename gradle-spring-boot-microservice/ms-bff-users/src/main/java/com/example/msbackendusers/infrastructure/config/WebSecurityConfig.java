package com.example.msbackendusers.infrastructure.config;

import com.example.jwttokenacces.infrastructure.jwt.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //desabilitar peticiones cruzadas
                .authorizeRequests()//Autorize las peticiones...
                .antMatchers(HttpMethod.POST,"/users/**").permitAll()
                .anyRequest().authenticated();//todas las demas pidan autentificacion

        http.addFilterBefore(getJwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtAuthFilter getJwtFilter(){
        return new JwtAuthFilter();
    }
}
