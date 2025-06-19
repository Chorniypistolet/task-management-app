package com.portfolioapp.taskamanagementapp.service;

import com.portfolioapp.taskamanagementapp.dto.project.ProjectRequestDto;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectResponseDto;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectSearchParameters;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectUpdateRequestDto;
import com.portfolioapp.taskamanagementapp.model.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    ProjectResponseDto getProjectById(Long id);

    ProjectResponseDto create(User user, ProjectRequestDto requestDto);

    List<ProjectResponseDto> findAllByUser(User user);

    ProjectResponseDto update(Long id, ProjectUpdateRequestDto requestDto);

    void delete(Long id);

    List<ProjectResponseDto> searchProjects(ProjectSearchParameters searchParameters,
                                            Pageable pageable);
}
