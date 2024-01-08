package com.tuto.api.services;

import com.tuto.api.entities.UserEntity;
import com.tuto.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void createUser(String username, String password, String role) {
        String encodePassword = passwordEncoder.encode(password);

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(encodePassword);
        user.setRole(role);

        userRepository.save(user);
    }
}
