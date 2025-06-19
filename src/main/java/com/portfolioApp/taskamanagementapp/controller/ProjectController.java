package com.portfolioapp.taskamanagementapp.controller;

import com.portfolioapp.taskamanagementapp.dto.project.ProjectRequestDto;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectResponseDto;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectSearchParameters;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectUpdateRequestDto;
import com.portfolioapp.taskamanagementapp.model.User;
import com.portfolioapp.taskamanagementapp.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/{id}")
    public ProjectResponseDto getProjectInfo(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ProjectResponseDto createProject(@AuthenticationPrincipal User user,
                                            @RequestBody ProjectRequestDto projectRequestDto) {
        return projectService.create(user, projectRequestDto);
    }

    @GetMapping
    public List<ProjectResponseDto> findAllProjectsByUser(@AuthenticationPrincipal User user) {
        return projectService.findAllByUser(user);
    }

    @PutMapping("/{id}")
    public ProjectResponseDto updateProject(@PathVariable Long id,
                                            @RequestBody ProjectUpdateRequestDto requestDto) {
        return projectService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }

    @GetMapping("/search")
    public List<ProjectResponseDto> searchProjectByParams(ProjectSearchParameters searchParameters,
                                                          @ParameterObject
                                                          @PageableDefault Pageable pageable) {
        return projectService.searchProjects(searchParameters, pageable);
    }
}
