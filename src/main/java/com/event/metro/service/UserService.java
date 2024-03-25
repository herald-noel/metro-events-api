package com.event.metro.service;

import com.event.metro.model.Event;
import com.event.metro.model.Participant;
import com.event.metro.model.Review;
import com.event.metro.model.User;
import com.event.metro.model.dto.ReviewDTO;
import com.event.metro.repository.EventRepository;
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

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;

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

    public int addUserReview(String eventId, ReviewDTO reviewDTO) {
        Review review = new Review(reviewDTO.getUsername(), reviewDTO.getComment());
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            return 0;
        }
        Event eventObj = event.get();
        eventObj.getReviewList().add(review);
        eventRepository.save(eventObj);
        return 1;
    }
}
