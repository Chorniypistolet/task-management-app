package com.portfolioapp.taskamanagementapp.controller;

import com.portfolioapp.taskamanagementapp.dto.project.ProjectIdRequestDto;
import com.portfolioapp.taskamanagementapp.dto.task.TaskRequestDto;
import com.portfolioapp.taskamanagementapp.dto.task.TaskResponseDto;
import com.portfolioapp.taskamanagementapp.dto.task.TaskSearchParameters;
import com.portfolioapp.taskamanagementapp.dto.task.TaskUpdateRequestDto;
import com.portfolioapp.taskamanagementapp.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public TaskResponseDto create(@RequestBody TaskRequestDto requestDto) {
        return taskService.createTask(requestDto);
    }

    @GetMapping
    public List<TaskResponseDto> getProjectTasks(@RequestBody ProjectIdRequestDto dto) {
        return taskService.getTasksListByProject(dto);
    }

    @GetMapping("/{id}")
    public TaskResponseDto getTaskDetails(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDto updateTask(@PathVariable Long id,
                                     @RequestBody TaskUpdateRequestDto requestDto) {
        return taskService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }

    @GetMapping("/all")
    public List<TaskResponseDto> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/search")
    public List<TaskResponseDto> getTaskByParameters(TaskSearchParameters searchParameters,
                                                     @ParameterObject
                                                     @PageableDefault Pageable pageable) {
        return taskService.getTaskByParameters(searchParameters, pageable);
    }
}
