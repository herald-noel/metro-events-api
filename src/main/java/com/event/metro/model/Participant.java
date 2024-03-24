package com.event.metro.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Participant {
    String participantId;
    String username;
    /*
     * 0 - For Approval
     * 1 - Approve
     * 2 - Decline
     */
    int status;

    public Participant(String username) {
        this.participantId = UUID.randomUUID().toString();
        this.username = username;
        this.status = 0;
    }
}
