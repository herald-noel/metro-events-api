package com.event.metro.model.data;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class Event {
    String title;
    LocalDateTime eventDateTimeCreated;
    LocalTime timeStart;
    LocalTime timeEnd;
    LocalDate dateStart;
    LocalDate dateEnd;
    String description;
}
