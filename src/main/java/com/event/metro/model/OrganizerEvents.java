package com.event.metro.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class OrganizerEvents {
    @Id
    String id;
    @NonNull
    String username;
    @NonNull
    List<Event> events;

    public OrganizerEvents(@NonNull String username) {
        this.username = username;
        this.events = new ArrayList<>();
    }
}
