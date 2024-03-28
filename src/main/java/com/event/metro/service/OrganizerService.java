package com.event.metro.service;

import com.event.metro.model.*;
import com.event.metro.model.dto.EventDTO;
import com.event.metro.repository.EventRepository;
import com.event.metro.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {

    final
    EventRepository eventRepository;

    final
    UserRepository userRepository;

    public OrganizerService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Event> addOrganizerEvent(EventDTO event) {
        Event newEvent = new Event(event.getOwner(), event.getTitle(), event.getTimeStart(), event.getTimeEnd(),
                event.getDateStart(), event.getDateEnd(), event.getDescription());
        String title = "UPCOMING EVENT: " + event.getTitle();
        String message = "A new event organized by " + event.getOwner() + ". Event starts on " + event.getDateStart();
        Notification notification = new Notification(title, message);
        List<User> userList = userRepository.findAll();
        userList.forEach(user -> {
            if (!user.getUsername().equals(event.getOwner())) {
                user.getNotificationList().add(notification);
                userRepository.save(user);
            }
        });
        return ResponseEntity.ok(eventRepository.save(newEvent));
    }

    public ResponseEntity<Optional<Event>> findByEventId(String eventId) {
        return ResponseEntity.ok(eventRepository.findById(eventId));
    }

    public ResponseEntity<List<Participant>> updateSuccessStatus(String eventId, String participantId) {
        return updateStatusAndNotify(eventId, participantId, 1);
    }

    public ResponseEntity<List<Participant>> updateDeclineStatus(String eventId, String participantId) {
        return updateStatusAndNotify(eventId, participantId, -1);
    }

    // Helper methods
    private ResponseEntity<List<Participant>> updateStatusAndNotify(String eventId, String participantId, int status) {
        UserJoinStatusNotification notification = new UserJoinStatusNotification();
        List<Participant> change = changeParticipantStatus(eventId, participantId, status, notification);
        addNotificationUser(notification.getUsername(), notification);
        return new ResponseEntity<>(change, HttpStatus.OK);
    }

    private List<Participant> changeParticipantStatus(String eventId, String participantId, int status, UserJoinStatusNotification notification) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            // Find the participant by participantId
            Participant participantToUpdate = event.getParticipantList().stream()
                    .filter(participant -> participant.getId().equals(participantId))
                    .findFirst().orElse(null);

            if (participantToUpdate == null) {
                return new ArrayList<>();
            }
            if (status == 1) {
                notification.setTitle("YOU HAVE BEEN ACCEPTED");
                notification.setMessage("Your request to join " + event.getTitle() + " has been accepted.");
            } else {
                notification.setTitle("YOU HAVE BEEN DECLINED");
                notification.setMessage("Your request to join " + event.getTitle() + " has been declined.");
            }
            notification.setUsername(participantToUpdate.getUsername());

            // Update participant status if found
            participantToUpdate.setStatus(status);

            eventRepository.save(event);
            return event.getParticipantList();
        }
        return new ArrayList<>();
    }

    private void addNotificationUser(String username, Notification notification) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getNotificationList().add(notification);
            userRepository.save(user);
        }
    }
}
