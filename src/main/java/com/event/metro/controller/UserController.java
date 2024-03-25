package com.event.metro.controller;

import com.event.metro.model.Event;
import com.event.metro.model.Review;
import com.event.metro.model.dto.ReviewDTO;
import com.event.metro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("join/{username}/{eventId}")
    public int joinEvent(@PathVariable String username, @PathVariable String eventId) {
        return userService.userJoinEvent(eventId, username);
    }

    @PostMapping("/event/{eventId}/review")
    public ResponseEntity<List<Review>>addUserReview(@PathVariable String eventId, @RequestBody ReviewDTO reviewDTO) {
        return userService.addUserReview(eventId, reviewDTO);
    }
}
