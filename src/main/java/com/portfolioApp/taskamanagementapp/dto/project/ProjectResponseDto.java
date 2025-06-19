package com.portfolioapp.taskamanagementapp.dto.project;

import com.portfolioapp.taskamanagementapp.dto.task.TaskResponseDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserShortResponseDto;
import com.portfolioapp.taskamanagementapp.model.ProjectStatus;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;
    private List<TaskResponseDto> tasks;
    private UserShortResponseDto user;
}
