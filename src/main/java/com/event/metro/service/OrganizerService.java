package com.event.metro.service;

import com.event.metro.model.Event;
import com.event.metro.model.OrganizerEvents;
import com.event.metro.repository.OrganizerEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizerService {

    @Autowired
    OrganizerEventRepository organizerEventRepository;

    public ResponseEntity<OrganizerEvents> addOrganizerEvent(String username, Event newEvent) {
        OrganizerEvents organizerEvents = organizerEventRepository.findByUsername(username).orElseThrow(null);
        if (organizerEvents != null) {
            organizerEvents.getEvents().add(newEvent);
            OrganizerEvents updated = organizerEventRepository.save(organizerEvents);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
}
