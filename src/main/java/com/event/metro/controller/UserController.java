package com.event.metro.controller;

import com.event.metro.model.Event;
import com.event.metro.model.Notification;
import com.event.metro.model.Review;
import com.event.metro.model.User;
import com.event.metro.model.dto.ReviewDTO;
import com.event.metro.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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

    @PostMapping("/event/{eventId}/{title}/{message}/cancel")
    public int organizerCancelEvent(@PathVariable String eventId, @PathVariable String title, @PathVariable String message) {
        userService.addNotification(eventId, title, message);
        return userService.cancelEvent(eventId);
    }

    @PostMapping("/{eventId}/upvote")
    public int upvoteEvent(@PathVariable String eventId, @RequestBody String username) {
        return userService.upvoteEvent(eventId, username);
    }
    @PostMapping("/{eventId}/{title}/{message}")
    public boolean addNotification(@PathVariable String eventId, @PathVariable String title, @PathVariable String message) {
        return userService.addNotification(eventId, title, message);
    }
    @GetMapping("/upvote/check/{eventId}/{username}")
    public boolean checkUpvoteUser(@PathVariable String eventId, @PathVariable String username) {
        return userService.isUserUpvoteEvent(eventId, username);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<List<User>> getParticipantsList(@PathVariable String eventId) {
        return userService.getParticipantListByEventId(eventId);
    }

    @GetMapping("notifications/{username}")
    public ResponseEntity<List<Notification>> getNotification(@PathVariable String username) {
        return userService.getNotificationByUsername(username);
    }

    @GetMapping("notification/{username}/IsSeen")
    public boolean setNotificationIsSeen(@PathVariable String username) {
        return userService.setNotificationIsSeen(username);
    }

    @GetMapping("notification/{username}/checkIsSeen")
    public boolean checkNotificationIsSeen(@PathVariable String username) {
        return userService.checkNotificationIsSeen(username);
    }
    @GetMapping("notification/{eventId}/{message}/reminder")
    public boolean checkNotificationIsSeen(@PathVariable String eventId, @PathVariable String message) {
        return userService.addEventReminder(eventId, message);
    }
}

