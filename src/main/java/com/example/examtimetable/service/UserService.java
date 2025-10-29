package com.example.examtimetable.service;

import com.example.examtimetable.model.User;
import com.example.examtimetable.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, BCryptPasswordEncoder passwordEncoder){
        this.repo = repo; this.passwordEncoder = passwordEncoder;
    }

    public User register(User u){
        // basic validation and hashing
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        if(u.getRole()==null) u.setRole("ROLE_STUDENT");
        return repo.save(u);
    }

    public Optional<User> findByUsername(String username){ return repo.findByUsername(username); }
}
