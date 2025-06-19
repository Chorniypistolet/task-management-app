package com.portfolioapp.taskamanagementapp.dto.task;

import com.portfolioapp.taskamanagementapp.model.Priority;
import com.portfolioapp.taskamanagementapp.model.TaskStatus;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskUpdateRequestDto {
    private String name;
    private String description;
    private Priority priority;
    private TaskStatus status;
    private LocalDate dueDate;
    private Long userId;
    private Long labelId;
}
