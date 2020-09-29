package com.services.thirdservice.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.services.thirdservice.db.entity.user.User;
import com.services.thirdservice.db.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public User create(User user) throws IOException {
        String rowNumber = userRepository.create(user);
        Integer id = Integer.parseInt(rowNumber) + 1;
        user.setId(id);
        return user;
    }
}
