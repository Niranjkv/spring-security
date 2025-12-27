package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authz->authz.requestMatchers("/public").permitAll().
        requestMatchers("/user").hasAnyRole("USER","ADMIN").
        requestMatchers("/admin").hasRole("ADMIN")
        .anyRequest().authenticated())
       //.formLogin(form->form.permitAll())
        .formLogin(Customizer.withDefaults())
        //.formLogin(form->form.permitAll().defaultSuccessUrl("/public"))
        .csrf(csrf->csrf.disable()
        //.httpBasic(withDefaults())
    );
        return http.build();
    }



    @Bean
    public UserDetailsService userService(PasswordEncoder passwordEncoder){
        UserDetails user = User.withUsername("USER").
            password(passwordEncoder.encode("password")).roles("USER").build();


        UserDetails admin = User.withUsername("priyanka").
            password(passwordEncoder.encode("adminpass")).roles("ADMIN").build();
        
        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public PasswordEncoder passwordEncode(){
        return new BCryptPasswordEncoder();
    }

}