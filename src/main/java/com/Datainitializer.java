package com;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.model.Auth;
import com.repository.UserRepository;

@Component
public class Datainitializer  implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByUsername("test")) { 
            Auth user = new Auth();
            user.setUsername("test");
            user.setPassword(passwordEncoder.encode("12345"));
            Auth usuario = userRepository.save(user);

            if (usuario != null) {
                System.out.println("Usuario guardado: " + usuario.getUsername());
            }
        }
    }
}