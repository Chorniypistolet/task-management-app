package com.portfolioapp.taskamanagementapp.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portfolioapp.taskamanagementapp.dto.label.LabelResponseDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserShortResponseDto;
import com.portfolioapp.taskamanagementapp.model.Priority;
import com.portfolioapp.taskamanagementapp.model.TaskStatus;
import lombok.Data;

@Data
public class TaskShortResponseDto {
    private String name;
    private Priority priority;
    private TaskStatus status;
    private UserShortResponseDto assignee;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LabelResponseDto label;
}
