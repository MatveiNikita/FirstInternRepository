package com.example.firstinternrepository.repository;

import com.example.firstinternrepository.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(String userId);
    Optional<User> getUsersById(String id);
    boolean existsById(String userId);
    boolean existsByUsername(String username);
}