package com.event.metro;

import com.event.metro.model.ApplicationUser;
import com.event.metro.model.Role;
import com.event.metro.repository.RoleRepository;
import com.event.metro.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableMongoRepositories
public class MetroApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetroApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
        };
    }
}
