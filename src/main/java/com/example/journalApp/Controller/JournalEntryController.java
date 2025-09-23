package com.example.journalApp.Controller;

import com.example.journalApp.entities.JournalEntry;
import com.example.journalApp.entities.Users;
import com.example.journalApp.services.JournalEntryService;
import com.example.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

//    @GetMapping("{username}")
//    public ResponseEntity<List<JournalEntry>> getAllEntriesOfUser(@PathVariable String username){
//        Users user = userService.findByUsername(username);
//        List<JournalEntry> journalEntries = user.getJournalEntries();
//        if(journalEntries!=null && !journalEntries.isEmpty())
//            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


    @GetMapping()
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<JournalEntry> journalEntries = journalEntryService.getJournalEntriesByUsername(username);
        if(journalEntries!=null && !journalEntries.isEmpty())
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myJournalEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            JournalEntry newEntry = journalEntryService.createEntry(myJournalEntry, username);
            return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = userService.findByUsername(username);
        List<JournalEntry> allEntriesOfUser = user.getJournalEntries();
        List<JournalEntry> currEntry = allEntriesOfUser.stream().filter( journal -> journal.getId().equals(myId)).toList();
        if(currEntry.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Optional<JournalEntry> entryById = journalEntryService.getEntryById(myId);
        if(entryById.isPresent())
            return new ResponseEntity<>(entryById.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        journalEntryService.deleteEntryById(id, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntryById(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry newJournalEntry
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = userService.findByUsername(username);
        List<JournalEntry> entryToUpdate = user.getJournalEntries().stream().filter(journalEntry -> journalEntry.getId().equals(id)).toList();
        if(!entryToUpdate.isEmpty()){
            JournalEntry oldJournalEntry = entryToUpdate.getFirst();
            if(!newJournalEntry.getTitle().isEmpty()){
                oldJournalEntry.setTitle(newJournalEntry.getTitle());
            }
            if(newJournalEntry.getContent()!=null && !newJournalEntry.getContent().isEmpty()){
                oldJournalEntry.setContent(newJournalEntry.getContent());
            }
            journalEntryService.createEntry(oldJournalEntry, username);
            return new ResponseEntity<>(oldJournalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
