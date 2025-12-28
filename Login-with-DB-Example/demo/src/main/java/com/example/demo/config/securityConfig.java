package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Autowired
    private CustomUserDetailsService cus;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenicationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider(cus);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth->
            auth.requestMatchers("/admin/**").hasAnyRole("admin")
            //.requestMatchers("/home").permitAll()
            .requestMatchers("/signup","/home").permitAll()
            .requestMatchers("/user/**").hasAnyRole("user")
            .anyRequest().authenticated()
        ).formLogin(form->form.permitAll().defaultSuccessUrl("/home"))
        .csrf(csrf->csrf.disable())
        ;
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }


}
