package com.event.metro.controller;

import com.event.metro.model.User;
import com.event.metro.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/")
    public String helloAdminController() {
        return "Admin access level.";
    }

    @GetMapping("/{id}/{role}")
    public ResponseEntity<User> promoteUser(@PathVariable String id, @PathVariable String role) {
        return adminService.promoteUser(id, role);
    }
}
