package com.portfolioapp.taskamanagementapp.dto.task;

import com.portfolioapp.taskamanagementapp.model.Priority;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private Priority priority;
    @NotBlank
    private LocalDate dueDate;
    @NotBlank
    private Long projectId;
    @NotBlank
    private Long userId;
}
