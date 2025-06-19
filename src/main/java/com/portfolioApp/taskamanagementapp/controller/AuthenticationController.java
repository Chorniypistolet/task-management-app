package com.portfolioapp.taskamanagementapp.controller;

import com.portfolioapp.taskamanagementapp.dto.user.UserLoginRequestDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserLoginResponseDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserRegistrationRequestDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserResponseDto;
import com.portfolioapp.taskamanagementapp.security.AuthenticationService;
import com.portfolioapp.taskamanagementapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto loginRequestDto) {
        return authenticationService.authenticate(loginRequestDto);
    }
}
