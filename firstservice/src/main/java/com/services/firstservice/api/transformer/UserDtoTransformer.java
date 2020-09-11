package com.services.firstservice.api.transformer;

import com.services.firstservice.api.dto.UserDto;
import com.services.firstservice.db.entity.user.User;

public class UserDtoTransformer {
    private static User buildUser(UserDto userDto, Long id) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .middleName(userDto.getMiddleName())
                .lastName(userDto.getLastName())
                .build();
    }
    
    public static User transform(UserDto userDto) {
        return buildUser(userDto, userDto.getId());
    }
    
    public static User transform(UserDto userDto, Long id) {
        return buildUser(userDto, id);
    }

    public static UserDto transform(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .build();
    }
}
