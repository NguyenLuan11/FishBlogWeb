package com.tnluan.fishblogweb.mapper;

import com.tnluan.fishblogweb.dto.UserDto;
import com.tnluan.fishblogweb.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getAvatarUrl(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getPhone(),
                user.getAddress(),
                user.getRegisteredDate(),
                user.getModifiedDate()
        );
    }

    public static User mapToUserEntity(UserDto userDto) {
        return new User(
                userDto.getUserName(),
                userDto.getAvatarUrl(),
                userDto.getFullName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole(),
                userDto.getPhone(),
                userDto.getAddress()
        );
    }

    public static List<UserDto> mapUserListToDtoList(List<User> userList) {
        return userList.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }
}
