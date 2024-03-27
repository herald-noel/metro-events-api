package com.event.metro.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Username {
    String username;

    public Username(String username) {
        this.username = username;
    }
}
