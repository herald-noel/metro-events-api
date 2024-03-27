package com.event.metro.service;

import com.event.metro.model.*;
import com.event.metro.model.dto.ReviewDTO;
import com.event.metro.repository.EventRepository;
import com.event.metro.repository.RequestRoleRepository;
import com.event.metro.repository.UserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
    final
    UserRepository userRepository;
    final
    EventRepository eventRepository;
    final
    RequestRoleRepository requestRoleRepository;
    final
    MongoTemplate mongoTemplate;

    public UserService(UserRepository userRepository, EventRepository eventRepository, RequestRoleRepository requestRoleRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.requestRoleRepository = requestRoleRepository;
        this.mongoTemplate = mongoTemplate;
    }

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
        List<Event> events = eventRepository.findAll();
        List<Event> userEvents = (events.stream().filter(Event::isActive).toList());

        return ResponseEntity.ok(userEvents);
    }

    public ResponseEntity<List<Event>> getAllOrganizerEventsByUsername(String username) {
        List<Event> events = eventRepository.findAll();

        List<Event> userEvents = events.stream()
                .filter(event -> event.getOwner().equals(username) && event.isActive()).collect(Collectors.toList());

        return ResponseEntity.ok(userEvents);
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

    public int cancelEvent(String eventId) {
        /*
         * 0 - failed
         * 1 - success
         */
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isEmpty()) {
            return 0;
        }
        Event eventObj = event.get();
        eventObj.setActive(false);
        eventRepository.save(eventObj);
        return 1;
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

    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public int upvoteEvent(String eventId, String username) {
        /*
         * 0 - remove
         * 1 - add
         */
        if (isUserUpvoteEvent(eventId, username)) {
            removeUpvote(eventId, username);
            return 0;
        } else {
            addUpvote(eventId, username);
            return 1;
        }
    }

    public boolean isUserUpvoteEvent(String eventId, String username) {
        Query query = new Query(Criteria.where("_id").is(eventId).and("upvoteList.username").is(username));
        return mongoTemplate.exists(query, Event.class);
    }

    private void removeUpvote(String eventId, String username) {
        Query query = new Query(Criteria.where("_id").is(eventId).and("upvoteList.username").is(username));
        Update update = new Update().pull("upvoteList", new Username(username));
        mongoTemplate.updateFirst(query, update, Event.class);
    }

    private void addUpvote(String eventId, String username) {
        Update update = new Update().addToSet("upvoteList", new Username(username));
        mongoTemplate.upsert(new Query(Criteria.where("_id").is(eventId)), update, Event.class);
    }


}
