package com.event.metro.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelEventNotification extends Notification {
    String eventId;


    public CancelEventNotification(String eventId, String title, String message) {
        super(title, message);
        this.eventId = eventId;
    }
}
