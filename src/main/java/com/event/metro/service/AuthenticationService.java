package com.event.metro.service;

import com.event.metro.model.dto.SignupDTO;
import com.event.metro.model.ApplicationUser;
import com.event.metro.model.Role;
import com.event.metro.repository.RoleRepository;
import com.event.metro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    public ApplicationUser signup(SignupDTO dto) {
        ApplicationUser user = new ApplicationUser(dto.getUsername(), dto.getEmail(), passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(user);
    }
}
