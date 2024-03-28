package com.event.metro.model;

import java.util.UUID;

public class Notification {
    String id;
    String eventId;
    boolean isSeen;
    String message;

    public Notification(String eventId, String message) {
        this.id = UUID.randomUUID().toString();
        this.eventId = eventId;
        this.isSeen = false;
        this.message = message;
    }
}
