package com.event.metro.repository;

import com.event.metro.model.OrganizerEvents;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrganizerEventRepository extends MongoRepository<OrganizerEvents, String> {
    Optional<OrganizerEvents> findByUsername(String username);
}
