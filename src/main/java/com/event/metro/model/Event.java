package com.event.metro.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class Event {
    @Id
    String eventId;
    @NonNull String title;
    @NonNull LocalDateTime eventDateTimeCreated;
    @NonNull LocalTime timeStart;
    @NonNull LocalTime timeEnd;
    @NonNull LocalDate dateStart;
    @NonNull LocalDate dateEnd;
    @NonNull String description;

    public Event(String eventId, @NonNull String title, @NonNull LocalDateTime eventDateTimeCreated,
                 @NonNull LocalTime timeStart, @NonNull LocalTime timeEnd, @NonNull LocalDate dateStart,
                 @NonNull LocalDate dateEnd, @NonNull String description) {
        this.eventId = eventId;
        this.title = title;
        this.eventDateTimeCreated = eventDateTimeCreated;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }
}
