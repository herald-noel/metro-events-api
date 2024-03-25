package com.event.metro.controller;

import com.event.metro.model.Event;
import com.event.metro.model.dto.EventDTO;
import com.event.metro.model.dto.RequestJoinDTO;
import com.event.metro.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/organizers")
public class OrganizerController {
    @Autowired
    OrganizerService organizerService;

    @PostMapping("/create/event")
    public ResponseEntity<Event> createEvent(@RequestBody EventDTO eventDTO) {
        return organizerService.addOrganizerEvent(eventDTO);
    }

    @GetMapping("/event/details/{id}")
    public ResponseEntity<Optional<Event>> getEventById(@PathVariable String id) {
        return organizerService.findByEventId(id);
    }

    @PostMapping("/accept")
    public String acceptParticipant(@RequestBody RequestJoinDTO requestJoinDTO) {
        return organizerService.updateSuccessStatus(requestJoinDTO.getEventId(), requestJoinDTO.getParticipantId());
    }

    @PostMapping("/decline")
    public String declineParticipant(@RequestBody RequestJoinDTO requestJoinDTO) {
        return organizerService.updateDeclineStatus(requestJoinDTO.getEventId(), requestJoinDTO.getParticipantId());
    }
}
