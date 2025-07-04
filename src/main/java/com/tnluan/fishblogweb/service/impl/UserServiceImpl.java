package com.tnluan.fishblogweb.service.impl;

import com.tnluan.fishblogweb.dto.UserDto;
import com.tnluan.fishblogweb.entity.User;
import com.tnluan.fishblogweb.exception.ResourceConflictException;
import com.tnluan.fishblogweb.exception.ResourceNotFoundException;
import com.tnluan.fishblogweb.exception.ResourceUnprocessableEntityException;
import com.tnluan.fishblogweb.mapper.UserMapper;
import com.tnluan.fishblogweb.repository.UserRepository;
import com.tnluan.fishblogweb.service.UserService;
import com.tnluan.fishblogweb.util.BcryptPass;
import com.tnluan.fishblogweb.util.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UploadService uploadService;

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User isn't exists with given id: " + id)
        );
    }

    private void checkExistInfoUser(String userName, String email) {
        if (userRepository.existsByUserName(userName)) {
            throw new ResourceConflictException("Username already exists! Please choose another one!");
        }
        if (userRepository.existsByEmail(email)) {
            throw new ResourceConflictException("Email already exists! Please choose another one!");
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userDto.getUserName() == null || userDto.getUserName().trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Username is required! Please provide a valid userName!");
        }
        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Email is required! Please provide a valid email!");
        }
        if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Password is required! Please provide a valid password!");
        }
        if (userDto.getRole() == null || userDto.getRole().trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Role is required! Please provide a valid role!");
        }

        // Check exist userName or email
        checkExistInfoUser(userDto.getUserName(), userDto.getEmail());

        // Encrypt password
        userDto.setPassword(BcryptPass.encrypt(userDto.getPassword()));

        User user = UserMapper.mapToUserEntity(userDto);
        User saveUser = userRepository.save(user);
        return UserMapper.mapToUserDto(saveUser);
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) {
        if (userDto.getUserName() == null || userDto.getUserName().trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Username is required! Please provide a valid userName!");
        }
        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Email is required! Please provide a valid email!");
        }

        // Check exist userName or email
        checkExistInfoUser(userDto.getUserName(), userDto.getEmail());

        User user = findUserById(id);

        user.setUserName(userDto.getUserName());
        user.setFullName(userDto.getFullName());
        user.setAvatarUrl(userDto.getAvatarUrl());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());

        User updateUser = userRepository.save(user);
        return UserMapper.mapToUserDto(updateUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        return UserMapper.mapToUserDto(findUserById(id));
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> listUser = userRepository.findAll();
        return UserMapper.mapUserListToDtoList(listUser);
    }

    @Override
    public void deleteUserById(Long id) {
        UserDto user = UserMapper.mapToUserDto(findUserById(id));

        if (user.getAvatarUrl() != null || !user.getAvatarUrl().isEmpty()) {
            uploadService.deleteImage(user.getAvatarUrl());
        }

        userRepository.deleteById(id);
    }

    @Override
    public UserDto loginAccountUser(String userName, String password) {
        User loginUser = userRepository.findByUserName(userName).orElseThrow(
                () -> new ResourceNotFoundException("User's account isn't exists! Maybe wrong username or password!")
        );

        if (!BcryptPass.matchesPassword(password, loginUser.getPassword())) {
            throw new ResourceNotFoundException("Invalid username or password!");
        }

        return UserMapper.mapToUserDto(loginUser);
    }

    @Override
    public UserDto updatePasswordAccountUserById(Long id, String oldPassword, String newPassword) {
        if (oldPassword == null || oldPassword.trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Current password is required! Please provide your current password!");
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("New password is required! Please provide a valid password!");
        }

        User user = findUserById(id);

        // Check match password
        if (!BcryptPass.matchesPassword(oldPassword, user.getPassword())) {
            throw new ResourceUnprocessableEntityException("Current password is incorrect! Please enter the correct current password!");
        }

        user.setPassword(BcryptPass.encrypt(newPassword));
        User updatePasswordUser = userRepository.save(user);
        return UserMapper.mapToUserDto(updatePasswordUser);
    }

    @Override
    public UserDto updateRoleUserById(Long id, String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new ResourceUnprocessableEntityException("Role is required! Please provide a valid role!");
        }

        User user = findUserById(id);

        user.setRole(role);
        User updateRoleUser = userRepository.save(user);
        return UserMapper.mapToUserDto(updateRoleUser);
    }
}
