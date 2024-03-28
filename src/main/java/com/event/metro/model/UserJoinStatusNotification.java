package com.event.metro.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinStatusNotification extends Notification {
    private String username;

    public UserJoinStatusNotification() {
    }

    public UserJoinStatusNotification(String title, String message, String username) {
        super(title, message);
        this.username = username;
    }
}
