package com.services.firstservice.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.services.firstservice.db.entity.user.User;
import com.services.firstservice.db.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Value("${secondService.url}")
    private String secondServiceUrl;

    public User get(Integer id) {
        User user = userRepository.findById(id).get();
        if (user != null) {
            return user;
        } else {
            throw new NoSuchElementException();
        }
    }

    public User create(User user) {
        HttpEntity<User> entity = new HttpEntity<User>(user);
        ResponseEntity<User> responseEntity = restTemplate.exchange(secondServiceUrl, HttpMethod.POST,
                entity,
                User.class);
        User createdUser = responseEntity.getBody();
        createdUser.setLastName(user.getLastName());
        log.info("Created User: {}", createdUser);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("Successful response");
            userRepository.updateLastNameById(createdUser.getLastName(), createdUser.getId());
            return createdUser;
        } else {
            throw new IllegalStateException();
        }
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
