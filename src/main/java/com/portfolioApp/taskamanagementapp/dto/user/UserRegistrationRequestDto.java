package com.portfolioapp.taskamanagementapp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegistrationRequestDto {
    @NotBlank
    private String userName;
    @NotBlank
    @Length(min = 6, max = 30)
    private String password;
    @NotBlank
    @Email
    private String email;
    private String firstName;
    private String lastName;
}
