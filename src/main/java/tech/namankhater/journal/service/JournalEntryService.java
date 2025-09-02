package tech.namankhater.journal.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.namankhater.journal.entity.JournalEntry;
import tech.namankhater.journal.entity.UserEntity;
import tech.namankhater.journal.repository.JournalEntryRepository;

@Service
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    
    @Autowired
    private UserService userService;
    
    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }
    
    public List<JournalEntry> getEntriesByUserId(String userId) {
        return journalEntryRepository.findByUserId(userId);
    }
    
    public Optional<JournalEntry> getEntryById(String id) {
        return journalEntryRepository.findById(id);
    }
    
    public JournalEntry saveEntry(JournalEntry entry, String userName) {
        UserEntity user = userService.getUserByUserName(userName);
        if (user != null) {
            entry.setUserId(user.getId());
            entry.setCreatedAt(new Date());
            return journalEntryRepository.save(entry);
        }
        throw new RuntimeException("User not found: " + userName);
    }
    
    public JournalEntry saveEntry(JournalEntry entry) {
        entry.setCreatedAt(new Date());
        return journalEntryRepository.save(entry);
    }
    
    public boolean deleteEntry(String id, String userName) {
        Optional<JournalEntry> entry = journalEntryRepository.findById(id);
        if (entry.isPresent()) {
            UserEntity user = userService.getUserByUserName(userName);
            if (user != null && entry.get().getUserId().equals(user.getId())) {
                journalEntryRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteEntry(String id) {
        if (journalEntryRepository.existsById(id)) {
            journalEntryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
