package com.services.thirdservice.service;

import org.springframework.stereotype.Service;

import com.services.thirdservice.db.entity.user.User;
import com.services.thirdservice.db.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) {
        user = userRepository.save(user);
        log.info("Created user: {}", user);
        return user;
    }
}
