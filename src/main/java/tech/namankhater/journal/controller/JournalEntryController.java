package tech.namankhater.journal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.namankhater.journal.entity.JournalEntry;
import tech.namankhater.journal.service.JournalEntryService;

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {
    
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/entries")
    public List<JournalEntry> getAllEntries() {
        return journalEntryService.getAllEntries();
    }

    @GetMapping("/entries/{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable String id) {
        System.out.println("Fetching journal entry with ID: " + id);
        Optional<JournalEntry> entry = journalEntryService.getEntryById(id);
        return entry.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/entries")
    public JournalEntry createEntry(@RequestBody JournalEntry entry) {
        return journalEntryService.saveEntry(entry);
    }

    @PutMapping("/entries/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable String id, @RequestBody JournalEntry entry) {
        Optional<JournalEntry> existingEntry = journalEntryService.getEntryById(id);
        if (existingEntry.isPresent()) {
            entry.setId(id);
            return ResponseEntity.ok(journalEntryService.saveEntry(entry));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable String id) {
        Optional<JournalEntry> existingEntry = journalEntryService.getEntryById(id);
        if (existingEntry.isPresent()) {
            journalEntryService.deleteEntry(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
