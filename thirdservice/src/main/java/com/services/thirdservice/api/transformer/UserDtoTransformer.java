package com.services.thirdservice.api.transformer;

import com.services.thirdservice.api.dto.UserDto;
import com.services.thirdservice.db.entity.user.User;

public class UserDtoTransformer {
    public static User transform(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .build();
    }
    
    public static UserDto transform(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .build();
    }
}
