package com.services.firstservice.api.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.services.firstservice.api.dto.UserDto;
import com.services.firstservice.api.transformer.UserDtoTransformer;
import com.services.firstservice.db.entity.user.User;
import com.services.firstservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ValueRange> get(@PathVariable Integer id) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.get(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) throws IOException {
        User user = UserDtoTransformer.transform(userDto);
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(UserDtoTransformer.transform(userService.create(user)));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Integer id, @RequestBody UserDto userDto)
            throws IOException, GeneralSecurityException {
        User user = userService.update(UserDtoTransformer.transform(userDto, id));
        return UserDtoTransformer.transform(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws IOException {
        userService.delete(id);
    }

    @PostMapping("/create")
    public void createNewSpreadsheet() throws IOException {
        userService.createNewSpreadsheet();
    }
}