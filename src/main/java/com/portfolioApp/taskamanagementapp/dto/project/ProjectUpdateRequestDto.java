package com.portfolioapp.taskamanagementapp.dto.project;

import com.portfolioapp.taskamanagementapp.model.ProjectStatus;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ProjectUpdateRequestDto {
    private String name;
    private String description;
    private LocalDate endDate;
    private ProjectStatus status;
    private Long userId;
}
