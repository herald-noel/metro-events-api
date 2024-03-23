package com.event.metro.repository;

import com.event.metro.model.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<ApplicationUser, String> {
    Optional<ApplicationUser> findByUsername(String username);
}
