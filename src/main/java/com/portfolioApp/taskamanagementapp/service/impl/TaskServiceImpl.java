package com.portfolioapp.taskamanagementapp.service.impl;

import com.portfolioapp.taskamanagementapp.dto.project.ProjectIdRequestDto;
import com.portfolioapp.taskamanagementapp.dto.task.TaskRequestDto;
import com.portfolioapp.taskamanagementapp.dto.task.TaskResponseDto;
import com.portfolioapp.taskamanagementapp.dto.task.TaskSearchParameters;
import com.portfolioapp.taskamanagementapp.dto.task.TaskUpdateRequestDto;
import com.portfolioapp.taskamanagementapp.exception.EntityNotFoundException;
import com.portfolioapp.taskamanagementapp.mapper.TaskMapper;
import com.portfolioapp.taskamanagementapp.model.Label;
import com.portfolioapp.taskamanagementapp.model.Project;
import com.portfolioapp.taskamanagementapp.model.Task;
import com.portfolioapp.taskamanagementapp.model.User;
import com.portfolioapp.taskamanagementapp.repository.label.LabelRepository;
import com.portfolioapp.taskamanagementapp.repository.project.ProjectRepository;
import com.portfolioapp.taskamanagementapp.repository.task.TaskRepository;
import com.portfolioapp.taskamanagementapp.repository.task.TaskSpecificationBuilder;
import com.portfolioapp.taskamanagementapp.repository.user.UserRepository;
import com.portfolioapp.taskamanagementapp.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    private final TaskSpecificationBuilder specificationBuilder;

    @Override
    public TaskResponseDto createTask(TaskRequestDto requestDto) {
        Task task = taskMapper.toEntity(requestDto);
        Project project = projectRepository.findByIdWithTasksAndAssignees(requestDto.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project with id "
                        + requestDto.getProjectId() + " is not found"));
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: "
                        + requestDto.getUserId()));
        List<Task> tasks = project.getTasks();
        task.setProject(project);
        task.setAssignee(user);
        tasks.add(task);
        taskRepository.save(task);
        projectRepository.save(project);
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskResponseDto> getTasksListByProject(ProjectIdRequestDto requestDto) {
        Project project = projectRepository.findByIdWithTasksAndAssignees(requestDto.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project with id "
                        + requestDto.getProjectId() + " is not found"));
        return taskMapper.toTaskDtosList(project.getTasks());
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task with id "
                        + id + " is not found"));
        return taskMapper.toDto(task);
    }

    @Override
    public TaskResponseDto update(Long id, TaskUpdateRequestDto requestDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task with id "
                        + id + " is not found"));
        taskMapper.updateFromDto(requestDto, task);
        if (requestDto.getUserId() != null) {
            User user = userRepository.findById(requestDto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User with id: "
                            + requestDto.getUserId() + " not found"));
            task.setAssignee(user);
        }
        if (requestDto.getLabelId() != null) {
            Label label = labelRepository.findById(requestDto.getLabelId())
                    .orElseThrow(() -> new EntityNotFoundException("Label with id "
                            + requestDto.getLabelId() + " is not found"));
            task.setLabel(label);
        }
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskResponseDto> findAll() {
        return taskMapper.toTaskDtosList(taskRepository.findAllActive());
    }

    @Override
    public List<TaskResponseDto> getTaskByParameters(TaskSearchParameters searchParameters,
                                                     Pageable pageable) {
        Specification<Task> spec = specificationBuilder
                .build(searchParameters);
        List<TaskResponseDto> dtoList = taskRepository.findAll(spec, pageable).stream()
                .map(taskMapper::toDto)
                .toList();
        if (dtoList.isEmpty()) {
            throw new EntityNotFoundException("Task with parameters " + searchParameters
                    + " are not found");
        }
        return dtoList;
    }
}
