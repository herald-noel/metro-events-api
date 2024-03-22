package com.event.metro.Controller;

import com.event.metro.Entity.User;
import com.event.metro.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.save(user);
        return user.get_id();
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getUsers() {
        return userService.getAllUsers();
    }
}
