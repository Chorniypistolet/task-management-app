package com.portfolioapp.taskamanagementapp.service.impl;

import com.portfolioapp.taskamanagementapp.dto.project.ProjectRequestDto;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectResponseDto;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectSearchParameters;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectUpdateRequestDto;
import com.portfolioapp.taskamanagementapp.exception.EntityNotFoundException;
import com.portfolioapp.taskamanagementapp.mapper.ProjectMapper;
import com.portfolioapp.taskamanagementapp.model.Project;
import com.portfolioapp.taskamanagementapp.model.User;
import com.portfolioapp.taskamanagementapp.repository.project.ProjectRepository;
import com.portfolioapp.taskamanagementapp.repository.project.ProjectSpecificationBuilder;
import com.portfolioapp.taskamanagementapp.repository.user.UserRepository;
import com.portfolioapp.taskamanagementapp.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;
    private final ProjectSpecificationBuilder specificationBuilder;

    @Override
    public ProjectResponseDto getProjectById(Long id) {
        Project project = projectRepository.findByIdWithTasksAndAssignees(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return projectMapper.toResponseDto(project);
    }

    @Override
    public ProjectResponseDto create(User user, ProjectRequestDto requestDto) {
        Project project = projectMapper.toEntity(requestDto, user);
        projectRepository.save(project);
        return projectMapper.toResponseDto(project);
    }

    @Override
    public List<ProjectResponseDto> findAllByUser(User user) {
        List<Project> projects = projectRepository.findAllByUserIdWithTasks(user.getId());
        return projectMapper.toListResponseDto(projects);
    }

    @Override
    public ProjectResponseDto update(Long id, ProjectUpdateRequestDto requestDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project with id: "
                        + id + " not found"));
        projectMapper.updateProjectFromDto(requestDto, project);
        if (requestDto.getUserId() != null) {
            User user = userRepository.findById(requestDto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User with id: "
                            + requestDto.getUserId() + " not found"));
            project.setUser(user);
        }
        projectRepository.save(project);
        return projectMapper.toResponseDto(project);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectResponseDto> searchProjects(ProjectSearchParameters searchParameters,
                                                   Pageable pageable) {
        Specification<Project> projectSpecification = specificationBuilder
                .build(searchParameters);
        List<ProjectResponseDto> dtoList = projectRepository
                .findAll(projectSpecification, pageable).stream()
                .map(projectMapper::toResponseDto)
                .toList();
        if (dtoList.isEmpty()) {
            throw new EntityNotFoundException("Project with parameters " + searchParameters
                    + " are not found");
        }
        return dtoList;
    }
}
