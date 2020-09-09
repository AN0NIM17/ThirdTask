package com.services.thirdservice.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.thirdservice.service.UserService;
import com.services.thirdservice.api.dto.UserDto;
import com.services.thirdservice.api.transformer.UserDtoTransformer;
import com.services.thirdservice.db.entity.user.User;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        User user = UserDtoTransformer.transform(userDto);
        user = userService.create(user);
        userDto = UserDtoTransformer.transform(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}
