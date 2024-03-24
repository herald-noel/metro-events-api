package com.event.metro.controller;

import com.event.metro.model.Event;
import com.event.metro.model.OrganizerEvents;
import com.event.metro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String helloUserController() {
        return "User access level.";
    }

    @GetMapping("/allEvents")
    public ResponseEntity<List<Event>> showAllEvents() {
        return userService.getAllEvents();
    }
}
