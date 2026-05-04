package com.wedding.backend.config;

import com.wedding.backend.entity.User;
import com.wedding.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoaderConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (!userRepository.existsByEmail("admin@wedding.com")) {
                User admin = User.builder()
                        .name("Admin User")
                        .email("admin@wedding.com")
                        .password(passwordEncoder.encode("password123"))
                        .build();
                userRepository.save(admin);
                System.out.println("Sample user created: admin@wedding.com / password123");
            }
        };
    }
}
