package com.event.metro.service;

import com.event.metro.model.Event;
import com.event.metro.model.OrganizerEvents;
import com.event.metro.model.dto.EventDTO;
import com.event.metro.repository.OrganizerEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizerService {

    @Autowired
    OrganizerEventRepository organizerEventRepository;

    public ResponseEntity<OrganizerEvents> addOrganizerEvent(String username, EventDTO event) {
        Event newEvent = new Event(event.getTitle(), event.getTimeStart(), event.getTimeEnd(), event.getDateStart(),
                event.getDateEnd(), event.getDescription());
        Optional<OrganizerEvents> optionalOrganizerEvents = organizerEventRepository.findByUsername(username);
        if (optionalOrganizerEvents.isEmpty()) {
            organizerEventRepository.save(new OrganizerEvents(username));
            optionalOrganizerEvents = organizerEventRepository.findByUsername(username);
        }
        OrganizerEvents organizerEvents = optionalOrganizerEvents.orElseThrow();
        organizerEvents.getEvents().add(newEvent);
        OrganizerEvents updated = organizerEventRepository.save(organizerEvents);
        return ResponseEntity.ok(updated);
    }
}
