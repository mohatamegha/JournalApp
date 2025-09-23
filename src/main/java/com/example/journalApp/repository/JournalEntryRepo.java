package com.example.journalApp.repository;

import com.example.journalApp.entities.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {

    Optional<JournalEntry> findById(ObjectId id);
}
