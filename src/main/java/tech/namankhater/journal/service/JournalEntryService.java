package tech.namankhater.journal.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.namankhater.journal.entity.JournalEntry;
import tech.namankhater.journal.repository.JournalEntryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    
    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }
    
    public Optional<JournalEntry> getEntryById(String id) {
        return journalEntryRepository.findById(id);
    }
    
    public JournalEntry saveEntry(JournalEntry entry) {
        entry.setCreatedAt(new Date());
        return journalEntryRepository.save(entry);
    }
    
    public boolean deleteEntry(String id) {
        if (journalEntryRepository.existsById(id)) {
            journalEntryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
