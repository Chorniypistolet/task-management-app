package com.portfolioapp.taskamanagementapp.dto.label;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateLabelRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String color;
}
