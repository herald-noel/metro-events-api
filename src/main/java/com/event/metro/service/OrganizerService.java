package com.event.metro.service;

import com.event.metro.model.Event;
import com.event.metro.model.OrganizerEvents;
import com.event.metro.model.dto.EventDTO;
import com.event.metro.repository.EventRepository;
import com.event.metro.repository.OrganizerEventRepository;
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
}
