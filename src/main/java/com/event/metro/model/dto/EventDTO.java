package com.event.metro.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {
    @NonNull String owner;
    @NonNull String title;
    @NonNull String timeStart;
    @NonNull String timeEnd;
    @NonNull String dateStart;
    @NonNull String dateEnd;
    @NonNull String description;
}
