package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userdetail = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("USer not found"));
        
         return org.springframework.security.core.userdetails.User.withUsername(username)
         .password(userdetail.getPassword())
         .roles(userdetail.getRole())
         .build();
        
    }
    

}
