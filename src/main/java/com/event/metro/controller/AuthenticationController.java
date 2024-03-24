package com.event.metro.controller;

import com.event.metro.model.User;
import com.event.metro.model.dto.LoginDTO;
import com.event.metro.model.dto.LoginResponseDTO;
import com.event.metro.model.dto.SignupDTO;
import com.event.metro.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public User signUp(@RequestBody SignupDTO signupDTO) {
        return authenticationService.signup(signupDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginDTO body) {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
