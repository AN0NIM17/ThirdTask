package com.services.firstservice.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.api.services.sheets.v4.model.ValueRange;
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

    public ValueRange get(Integer id) throws IOException {
        ValueRange user = userRepository.get(id);
        if (user != null) {
            return user;
        } else {
            throw new NoSuchElementException();
        }
    }

    public User create(User user) throws IOException {
        HttpEntity<User> entity = new HttpEntity<User>(user);
        ResponseEntity<User> responseEntity = restTemplate.exchange(secondServiceUrl, HttpMethod.POST,
                entity,
                User.class);
        User createdUser = responseEntity.getBody();
        createdUser.setLastName(user.getLastName());
        log.info("Created User: {}", createdUser);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("Successful response");
            userRepository.create(createdUser);
            return createdUser;
        } else {
            throw new IllegalStateException();
        }
    }

    public User update(User user) throws IOException, GeneralSecurityException {
        userRepository.update(user);
        return user;
    }

    public void delete(Integer id) throws IOException {
        userRepository.delete(id);
    }
    
    public void createNewSpreadsheet() throws IOException {
        userRepository.createNewSpreadsheet();
    }
}