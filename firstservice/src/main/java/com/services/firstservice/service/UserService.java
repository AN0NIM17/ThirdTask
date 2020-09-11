package com.services.firstservice.service;

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

    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    public User create(User user) {
        HttpEntity<User> entity = new HttpEntity<User>(user);
        ResponseEntity<User> responseEntity = restTemplate.exchange("http://secondservice:8082/user", HttpMethod.POST,
                entity,
                User.class);
        User createdUser = responseEntity.getBody();
        createdUser.setLastName(user.getLastName());
        log.info("Created User: {}", createdUser);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return userRepository.save(createdUser);
        } else {
            return null;
        }
    }

    public User update(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
