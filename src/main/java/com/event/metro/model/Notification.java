package com.event.metro.model;

import com.event.metro.repository.NotificationInterface;
import lombok.Data;

import java.util.UUID;

@Data
public class Notification implements NotificationInterface {
    String id;
    boolean isSeen;
    String title;
    String message;

    public Notification(String title, String message) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.message = message;
        this.isSeen = false;
    }



    @Override
    public String showTitle() {
        return null;
    }

    @Override
    public String showMessage() {
        return null;
    }
}
