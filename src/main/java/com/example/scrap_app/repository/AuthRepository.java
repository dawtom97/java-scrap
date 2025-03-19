package com.example.scrap_app.repository;

import com.example.scrap_app.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// User repository

@Repository
public interface AuthRepository extends MongoRepository<UserModel, String> {
    Optional<UserModel> findByUsername(String username);
}