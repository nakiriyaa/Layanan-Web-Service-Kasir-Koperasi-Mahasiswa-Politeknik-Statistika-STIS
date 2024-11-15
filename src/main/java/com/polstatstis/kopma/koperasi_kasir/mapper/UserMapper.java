package com.polstatstis.kopma.koperasi_kasir.mapper;

import com.polstatstis.kopma.koperasi_kasir.dto.UserDto;
import com.polstatstis.kopma.koperasi_kasir.entity.User;

public class UserMapper {

    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
}
