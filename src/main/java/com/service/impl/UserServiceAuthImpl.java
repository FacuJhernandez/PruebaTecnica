package com.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.model.Auth;
import com.repository.UserRepository;

@Service
public class UserServiceAuthImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Auth user= userRepository.findByUsername(username)
                    .orElseThrow(()-> new UsernameNotFoundException("user not found"));

            
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
    
}
