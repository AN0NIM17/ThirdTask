package com.services.secondservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.services.secondservice.db.entity.user.User;
import com.services.secondservice.db.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    
    @Value("${thirdService.url}")
    private String thirdServiceUrl;

    public User create(User user) {
        HttpEntity<User> entity = new HttpEntity<User>(user);
        ResponseEntity<User> responseEntity = restTemplate.exchange(thirdServiceUrl, HttpMethod.POST,
                entity, User.class);
        User createdUser = responseEntity.getBody();
        createdUser.setMiddleName(user.getMiddleName());
        log.info("Created user: {}", createdUser);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("Successful response");
            userRepository.updateMiddleNameById(createdUser.getMiddleName(), createdUser.getId());
            return createdUser;
        } else {
            throw new RuntimeException();
        }
    }
}
