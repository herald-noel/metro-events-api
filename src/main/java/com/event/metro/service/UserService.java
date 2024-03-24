package com.event.metro.service;

import com.event.metro.model.Event;
import com.event.metro.model.OrganizerEvents;
import com.event.metro.model.User;
import com.event.metro.repository.OrganizerEventRepository;
import com.event.metro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrganizerEventRepository organizerEventRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user id not found"));
    }

    public ResponseEntity<List<OrganizerEvents>> getAllOrganizerEvents() {
        return new ResponseEntity<>(organizerEventRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> allEvents = new ArrayList<>();
        List<OrganizerEvents> organizerEventsList = organizerEventRepository.findAll();
        // Iterate over each document
        for (OrganizerEvents organizerEvents : organizerEventsList) {
            // Extract the events from each entity
            List<Event> events = organizerEvents.getEvents();
            if (!events.isEmpty()) {
                allEvents.addAll(events);
            }
        }
        return new ResponseEntity<>(allEvents, HttpStatus.OK);
    }
}
