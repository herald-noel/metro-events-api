package com.event.metro.model.dto;

import com.event.metro.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestJoinDTO {
    private String eventId;
    private String participantId;
}
