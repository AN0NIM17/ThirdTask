package com.services.thirdservice.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.services.thirdservice.service.UserService;

import lombok.RequiredArgsConstructor;

import com.services.thirdservice.api.dto.UserDto;
import com.services.thirdservice.api.transformer.UserDtoTransformer;
import com.services.thirdservice.db.entity.user.User;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto) {
        User user = UserDtoTransformer.transform(userDto);
        return UserDtoTransformer.transform(userService.create(user));
    }
}
