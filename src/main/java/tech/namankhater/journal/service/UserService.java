package tech.namankhater.journal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.namankhater.journal.entity.UserEntity;
import tech.namankhater.journal.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<UserEntity> getUserById(String id) {
        return userRepository.findById(id);
    }
    
    public UserEntity getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
    
    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
