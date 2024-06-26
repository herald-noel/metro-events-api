package com.event.metro.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Participant {
    String id;
    String username;
    /*
     * 1 - Approve
     * 0 - For Approval
     * -1 - Decline
     */
    int status;

    public Participant(String username) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.status = 0;
    }
}
