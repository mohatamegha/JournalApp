package com.example.journalApp.entities;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    ObjectId Id;
    @NonNull
    @Indexed(unique = true)
    String username;
    @NonNull
    String password;
    String email;
    boolean sentimentAnalysis;
    @DBRef
    List<JournalEntry> journalEntries;
    List<String> roles;
}
