package com.example.journalApp.repository;

import com.example.journalApp.entities.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<Users, ObjectId> {
    Users findByUsername(String username);
    void deleteByUsername(String username);
}
