package com.portfolioapp.taskamanagementapp.service.impl;

import com.portfolioapp.taskamanagementapp.dto.role.RoleRequestDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserRegistrationRequestDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserResponseDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserUpdateRequestDto;
import com.portfolioapp.taskamanagementapp.exception.EntityNotFoundException;
import com.portfolioapp.taskamanagementapp.exception.RegistrationException;
import com.portfolioapp.taskamanagementapp.mapper.UserMapper;
import com.portfolioapp.taskamanagementapp.model.Role;
import com.portfolioapp.taskamanagementapp.model.RoleName;
import com.portfolioapp.taskamanagementapp.model.User;
import com.portfolioapp.taskamanagementapp.repository.role.RoleRepository;
import com.portfolioapp.taskamanagementapp.repository.user.UserRepository;
import com.portfolioapp.taskamanagementapp.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException(String.format("User with this email: %s already exists",
                    requestDto.getEmail()));
        }
        User user = userMapper.toUser(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public void updateUserRole(Long id, RoleRequestDto roleRequestDto) {
        User user = userRepository.findByIdWithRoles(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Set<Role> roles = user.getRoles();
        roles.clear();
        RoleName name = roleRequestDto.getRoleName();
        Role role = roleRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Role not found: " + name));
        roles.add(role);
        userRepository.save(user);
    }

    @Override
    public UserResponseDto getUserInfo(User user) {
        User userFromDb = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toUserResponse(userFromDb);
    }

    @Override
    public UserResponseDto updateUser(User user, UserUpdateRequestDto requestDto) {
        User userFromDb = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userMapper.updateUserFromDto(requestDto, user);
        userRepository.save(userFromDb);
        return userMapper.toUserResponse(userFromDb);
    }
}
