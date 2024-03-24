package com.event.metro.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {
    @NonNull String title;
    @NonNull String eventDateTimeCreated;
    @NonNull String timeStart;
    @NonNull String timeEnd;
    @NonNull String dateStart;
    @NonNull String dateEnd;
    @NonNull String description;
}
