package com.example.journalApp.services;

import com.example.journalApp.entities.JournalEntry;
import com.example.journalApp.entities.Users;
import com.example.journalApp.repository.JournalEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Slf4j
@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    //private static final  Logger logger = (Logger) LoggerFactory.getLogger(JournalEntryService.class);

    public List<JournalEntry> getJournalEntriesByUsername(String username){
        Users user = userService.findByUsername(username);
        return user.getJournalEntries();
    }

    public List<JournalEntry> getAllEntries(){
        return journalEntryRepo.findAll();
    }

    @Transactional
    public JournalEntry createEntry(JournalEntry myJournalEntry, String username) {
        try{
            myJournalEntry.setDate(LocalDateTime.now());
            JournalEntry savedEntry = journalEntryRepo.save(myJournalEntry);
            Users user = userService.findByUsername(username);
            user.getJournalEntries().add(savedEntry);
            userService.saveUser(user);
            return myJournalEntry;
        }
        catch(Exception e){
            log.error("Error occurred. ",e);
            //System.out.print(e.getMessage());
//            log.warn("hahahha");
//            debug and trace is not enabled by default, we have to config them
            throw new RuntimeException("An error occured! "+e.getMessage());
        }
    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return Optional.ofNullable(journalEntryRepo.findById(id).orElse(null));
    }

    @Transactional
    public void deleteEntryById(ObjectId id, String username){
        try{
            Users user = userService.findByUsername(username);
            List<JournalEntry> journalEntries = user.getJournalEntries();
            boolean removed = journalEntries.removeIf(entry -> entry.getId().equals(id));
            if(removed) {
                user.setJournalEntries(journalEntries);
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}