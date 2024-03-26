package com.event.metro.service;

import com.event.metro.model.*;
import com.event.metro.model.dto.ReviewDTO;
import com.event.metro.repository.EventRepository;
import com.event.metro.repository.RequestRoleRepository;
import com.event.metro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    RequestRoleRepository requestRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user id not found"));
    }

    public ResponseEntity<List<Event>> getAllEvents() {
        return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Event>> findUserEventsByUsername(String username) {
        List<Event> events = eventRepository.findAll();

        List<Event> userEvents = events.stream()
                .filter(event -> event.getParticipantList().stream()
                        .anyMatch(participant -> participant.getUsername().equals(username) && participant.getStatus() == 1))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userEvents);
    }

    public int userJoinEvent(String eventId, String username) {
        /*
         * 0 - failed
         * 1 - success
         *-1 - username exist
         */
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            return 0;
        }
        Event eventObj = event.get();
        List<Participant> participantList = eventObj.getParticipantList();
        for (Participant participant : participantList) {
            if (participant.getUsername().equals(username)) {
                return -1;
            }
        }
        eventObj.addParticipant(username);
        eventRepository.save(eventObj);
        return 1;
    }

    public ResponseEntity<List<Review>> addUserReview(String eventId, ReviewDTO reviewDTO) {
        Review review = new Review(reviewDTO.getUsername(), reviewDTO.getComment());
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            return null;
        }
        Event eventObj = event.get();
        eventObj.getReviewList().add(review);
        eventRepository.save(eventObj);
        return new ResponseEntity<>(eventObj.getReviewList(), HttpStatus.OK);
    }

    public int requestPromotion(String username) {
        /*
         * 0 - Request already exist
         * 1 - Request sent successfully.
         */
        if (requestRoleRepository.findByUsername(username).isPresent()) {
            return 0;
        }
        RequestRole requestRole = new RequestRole(username);
        requestRoleRepository.save(requestRole);
        return 1;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public ResponseEntity<List<User>> getAllUsers() {
      return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }
}
