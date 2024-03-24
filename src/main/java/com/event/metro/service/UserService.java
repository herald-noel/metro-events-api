package com.event.metro.service;

import com.event.metro.model.Event;
import com.event.metro.model.User;
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
}
