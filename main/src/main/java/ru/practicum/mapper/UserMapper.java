package ru.practicum.mapper;

import ru.practicum.dto.User.NewUserDto;
import ru.practicum.dto.User.UserDto;
import ru.practicum.dto.User.UserShortDto;
import ru.practicum.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toUserDto (User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public static User toUser (UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .build();
    }

    public static User toUser (NewUserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .build();
    }

    public static UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public static List<UserDto> userListToUserDtoList (List<User> userList) {
        return userList.stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

}
