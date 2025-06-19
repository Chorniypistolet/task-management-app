package com.portfolioapp.taskamanagementapp.dto.project;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectIdRequestDto {
    @NotBlank
    private Long projectId;
}
