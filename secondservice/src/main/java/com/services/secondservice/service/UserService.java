package com.services.secondservice.service;

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
    
    public User create(User user) {
        HttpEntity<User> entity = new HttpEntity<User>(user);
        ResponseEntity<User> responseEntity = restTemplate.exchange("http://thirdservice:8083/user", HttpMethod.POST, entity, User.class);
        User createdUser = responseEntity.getBody();
        createdUser.setMiddleName(user.getMiddleName());
        log.info("Get user: {}", createdUser);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return userRepository.save(createdUser);
        } else {
            return null;
        }
    }
//    
//    public User update(Long id, User user) {
//        user.setId(id);
//        user = userRepository.save(user);
//        HttpEntity<User> entity = new HttpEntity<User>(user);
//        return restTemplate.exchange("http://thirdservice:8083", HttpMethod.PUT, entity, User.class).getBody();
//    }
}
