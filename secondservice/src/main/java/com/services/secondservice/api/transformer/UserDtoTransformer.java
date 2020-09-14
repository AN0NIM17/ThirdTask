package com.services.secondservice.api.transformer;

import com.services.secondservice.api.dto.UserDto;
import com.services.secondservice.db.entity.user.User;

public class UserDtoTransformer {

    public static User transform(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .middleName(userDto.getMiddleName())
                .build();
    }

    public static UserDto transform(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .build();
    }
}
