package com.portfolioapp.taskamanagementapp.service;

import com.portfolioapp.taskamanagementapp.dto.project.ProjectIdRequestDto;
import com.portfolioapp.taskamanagementapp.dto.task.TaskRequestDto;
import com.portfolioapp.taskamanagementapp.dto.task.TaskResponseDto;
import com.portfolioapp.taskamanagementapp.dto.task.TaskSearchParameters;
import com.portfolioapp.taskamanagementapp.dto.task.TaskUpdateRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto requestDto);

    List<TaskResponseDto> getTasksListByProject(ProjectIdRequestDto requestDto);

    TaskResponseDto getTaskById(Long id);

    TaskResponseDto update(Long id, TaskUpdateRequestDto requestDto);

    void delete(Long id);

    List<TaskResponseDto> findAll();

    List<TaskResponseDto> getTaskByParameters(TaskSearchParameters searchParameters,
                                              Pageable pageable);
}
