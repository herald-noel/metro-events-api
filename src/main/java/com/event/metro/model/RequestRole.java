package com.event.metro.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class RequestRole {
    @Id
    String requestId;
    String username;

    public RequestRole(String username) {
        this.username = username;
    }
}
