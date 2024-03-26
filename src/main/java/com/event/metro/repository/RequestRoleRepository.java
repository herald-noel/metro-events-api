package com.event.metro.repository;

import com.event.metro.model.RequestRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RequestRoleRepository extends MongoRepository<RequestRole, String> {

    Optional<RequestRole> findByUsername(String username);

    void deleteByUsername(String username);
}
