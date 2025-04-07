package com.tnluan.fishblogweb.service;

import com.tnluan.fishblogweb.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUserById(Long id, UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUser();

    void deleteUserById(Long id);

    UserDto loginAccountUser(String userName, String password);

    UserDto updatePasswordAccountUserById(Long id, String oldPassword, String newPassword);

    UserDto updateRoleUserById(Long id, String role);
}
