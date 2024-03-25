package com.event.metro.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
public class RequestRole {
    @Id
    String requestId;
    String username;
    String requestPromoteTo;

    public RequestRole(String username, String requestPromoteTo) {
        this.requestId = UUID.randomUUID().toString();
        this.username = username;
        this.requestPromoteTo = requestPromoteTo;
    }
}
