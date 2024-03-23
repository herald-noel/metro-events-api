package com.event.metro.service;

import com.event.metro.model.Role;
import com.event.metro.model.dto.LoginResponseDTO;
import com.event.metro.model.dto.SignupDTO;
import com.event.metro.model.ApplicationUser;
import com.event.metro.repository.RoleRepository;
import com.event.metro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.NoSuchElementException;
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
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public ApplicationUser signup(SignupDTO dto) {
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        ApplicationUser user = new ApplicationUser(dto.getUsername(), dto.getEmail(), passwordEncoder.encode(dto.getPassword()), authorities);
        return userRepository.save(user);
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }
    }
}
