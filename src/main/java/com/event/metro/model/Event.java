package com.event.metro.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Event {
    @NonNull String title;
    @NonNull String eventDateTimeCreated;
    @NonNull String timeStart;
    @NonNull String timeEnd;
    @NonNull String dateStart;
    @NonNull String dateEnd;
    @NonNull String description;
    List<Participant> participantList;

    public Event() {
        this.participantList = new ArrayList<>();
    }

    public Event(@NonNull String title, @NonNull String timeStart,
                 @NonNull String timeEnd, @NonNull String dateStart, @NonNull String dateEnd,
                 @NonNull String description) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.title = title;
        this.eventDateTimeCreated = localDateTime.toString();
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
        this.participantList = new ArrayList<>();
    }
}
