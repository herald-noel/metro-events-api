package com.event.metro.service;

import com.event.metro.model.Event;
import com.event.metro.model.Participant;
import com.event.metro.model.dto.EventDTO;
import com.event.metro.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizerService {

    @Autowired
    EventRepository eventRepository;

    public ResponseEntity<Event> addOrganizerEvent(EventDTO event) {
        Event newEvent = new Event(event.getOwner(), event.getTitle(), event.getTimeStart(), event.getTimeEnd(),
                event.getDateStart(), event.getDateEnd(), event.getDescription());
        return ResponseEntity.ok(eventRepository.save(newEvent));
    }

    public ResponseEntity<Optional<Event>> findByEventId(String eventId) {
        return ResponseEntity.ok(eventRepository.findById(eventId));
    }

    public String updateSuccessStatus(String eventId, String participantId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            // Find the participant by participantId
            Participant participantToUpdate = event.getParticipantList().stream()
                    .filter(participant -> participant.getParticipantId().equals(participantId))
                    .findFirst().orElse(null);

            // Update participant status if found
            if (participantToUpdate != null) {
                participantToUpdate.setStatus(1);
            } else {
                return "user not found.";
            }

            eventRepository.save(event);
            return "success";
        }
        return "event not found";
    }
}
