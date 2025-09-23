package com.example.journalApp.Controller;

import com.example.journalApp.entities.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryControllerOld {
    Map<ObjectId, JournalEntry> entries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getEntries(){
        return new ArrayList<>(entries.values());
    }

    @PostMapping
    public String addEntry(@RequestBody JournalEntry myJournalEntry){
        entries.put(myJournalEntry.getId(), myJournalEntry);
        return "post successful!";
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return entries.get(myId);
    }

    @DeleteMapping("/id/{id}")
    public boolean deleteEntryById(@PathVariable ObjectId id){
        if(entries.containsKey(id)){
            entries.remove(id);
            return true;
        }
        else{
            return false;
        }
    }

    @PutMapping("/id/{id}")
    public boolean updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry myJournalEntry){
        if(entries.containsKey(id)){
            entries.put(id, myJournalEntry);
            return true;
        }
        else
            return false;
    }
}
