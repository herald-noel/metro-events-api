package com.event.metro.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "events")
@Getter
@Setter
public class Event {
    @Id
    String eventId;
    @NonNull String owner;
    @NonNull String title;
    @NonNull String eventDateTimeCreated;
    @NonNull String timeStart;
    @NonNull String timeEnd;
    @NonNull String dateStart;
    @NonNull String dateEnd;
    @NonNull String description;
    boolean active;
    List<Participant> participantList;
    List<Review> reviewList;
    List<Username> upvoteList;

    public Event() {
        this.participantList = new ArrayList<>();
    }

    public Event(@NonNull String owner, @NonNull String title, @NonNull String timeStart,
                 @NonNull String timeEnd, @NonNull String dateStart, @NonNull String dateEnd,
                 @NonNull String description) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.owner = owner;
        this.title = title;
        this.eventDateTimeCreated = localDateTime.toString();
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
        this.participantList = new ArrayList<>();
        this.reviewList = new ArrayList<>();
        this.upvoteList = new ArrayList<>();
        this.active = true;
    }

    public void addReview(String username, String message) {
        Review review = new Review(username, message);
        this.reviewList.add(review);
    }

    public void addParticipant(String username) {
        Participant participant = new Participant(username);
        this.participantList.add(participant);
    }
}
