package com.example.webquiz.service;

import com.example.webquiz.exception.UserNotAvailableException;
import com.example.webquiz.model.NewUser;
import com.example.webquiz.model.Role;
import com.example.webquiz.model.User;
import com.example.webquiz.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(NewUser newUser) {
        if (userRepo.findUserByEmail(newUser.getEmail()).isPresent()) {
            throw new UserNotAvailableException("Email is not available: " + newUser.getEmail());
        }

        User user = new User(newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()), Role.ROLE_USER);
        userRepo.save(user);
    }
}
