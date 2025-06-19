package com.portfolioapp.taskamanagementapp.service;

import com.portfolioapp.taskamanagementapp.dto.role.RoleRequestDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserRegistrationRequestDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserResponseDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserUpdateRequestDto;
import com.portfolioapp.taskamanagementapp.exception.RegistrationException;
import com.portfolioapp.taskamanagementapp.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    void updateUserRole(Long id, RoleRequestDto roleRequestDto);

    UserResponseDto getUserInfo(User user);

    UserResponseDto updateUser(User user, UserUpdateRequestDto userUpdateRequestDto);
}
