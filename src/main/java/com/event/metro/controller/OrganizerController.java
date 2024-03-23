package com.event.metro.controller;

import com.event.metro.model.Event;
import com.event.metro.model.OrganizerEvents;
import com.event.metro.model.dto.CreateEventDTO;
import com.event.metro.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/organizers")
public class OrganizerController {
    @Autowired
    OrganizerService organizerService;
    @PostMapping("/create/event")
    public ResponseEntity<OrganizerEvents> createEvent(@RequestBody CreateEventDTO createEventDTO) {
        return organizerService.addOrganizerEvent(createEventDTO.getUsername(), createEventDTO.getEvent());
    }
}