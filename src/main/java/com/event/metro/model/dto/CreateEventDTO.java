package com.event.metro.model.dto;

import com.event.metro.model.Event;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CreateEventDTO {
    @NotBlank
    String username;
    @NonNull
    Event event;
}
