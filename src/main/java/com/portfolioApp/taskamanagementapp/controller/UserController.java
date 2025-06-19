package com.portfolioapp.taskamanagementapp.controller;

import com.portfolioapp.taskamanagementapp.dto.role.RoleRequestDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserResponseDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserUpdateRequestDto;
import com.portfolioapp.taskamanagementapp.model.User;
import com.portfolioapp.taskamanagementapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserRole(@PathVariable Long id, @RequestBody RoleRequestDto roleRequestDto) {
        userService.updateUserRole(id, roleRequestDto);
    }

    @GetMapping("/me")
    public UserResponseDto getUserProfileInfo(@AuthenticationPrincipal User user) {
        return userService.getUserInfo(user);
    }

    @PatchMapping("/me")
    public UserResponseDto updateUser(@AuthenticationPrincipal User user,
                                      @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.updateUser(user, userUpdateRequestDto);
    }
}
