package com.event.metro.controller;

import com.event.metro.model.Event;
import com.event.metro.model.Review;
import com.event.metro.model.User;
import com.event.metro.model.dto.ReviewDTO;
import com.event.metro.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/allEvents")
    public ResponseEntity<List<Event>> showAllEvents() {
        return userService.getAllEvents();
    }

    @GetMapping("organizer/{username}")
    public ResponseEntity<List<Event>> getAllOrganizerEventsByUsername(@PathVariable String username) {
        return userService.getAllOrganizerEventsByUsername(username);
    }

    @GetMapping("events/{userId}")
    public ResponseEntity<List<Event>> findEventsByUsername(@PathVariable String userId) {
        return userService.findUserEventsByUsername(userId);
    }

    @PostMapping("join/{username}/{eventId}")
    public int joinEvent(@PathVariable String username, @PathVariable String eventId) {
        return userService.userJoinEvent(eventId, username);
    }

    @PostMapping("/event/{eventId}/review")
    public ResponseEntity<List<Review>> addUserReview(@PathVariable String eventId, @RequestBody ReviewDTO reviewDTO) {
        return userService.addUserReview(eventId, reviewDTO);
    }

    @PostMapping("/request")
    public int userRequestRole(@RequestBody String username) {
        return userService.requestPromotion(username);
    }

    @PostMapping("/event/{eventId}/cancel")
    public int organizerCancelEvent(@PathVariable String eventId) {
        return userService.cancelEvent(eventId);
    }

    @PostMapping("/{eventId}/upvote")
    public int upvoteEvent(@PathVariable String eventId, @RequestBody String username) {
        return userService.upvoteEvent(eventId, username);
    }

    @GetMapping("/upvote/check/{eventId}/{username}")
    public boolean checkUpvoteUser(@PathVariable String eventId, @PathVariable String username) {
        return userService.isUserUpvoteEvent(eventId, username);
    }
}

