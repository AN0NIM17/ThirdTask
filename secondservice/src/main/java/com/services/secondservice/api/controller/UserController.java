package com.services.secondservice.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.secondservice.api.dto.UserDto;
import com.services.secondservice.api.transformer.UserDtoTransformer;
import com.services.secondservice.db.entity.user.User;
import com.services.secondservice.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        log.info("Received user from firstService: {}", userDto);
        User user = UserDtoTransformer.transform(userDto);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(UserDtoTransformer.transform(userService.create(user)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
