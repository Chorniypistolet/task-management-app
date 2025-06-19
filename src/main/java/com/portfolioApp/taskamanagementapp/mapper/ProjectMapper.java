package com.portfolioapp.taskamanagementapp.mapper;

import com.portfolioapp.taskamanagementapp.config.MapperConfig;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectRequestDto;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectResponseDto;
import com.portfolioapp.taskamanagementapp.dto.project.ProjectUpdateRequestDto;
import com.portfolioapp.taskamanagementapp.model.Project;
import com.portfolioapp.taskamanagementapp.model.User;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class)
public interface ProjectMapper {

    ProjectResponseDto toResponseDto(Project project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "status",
            expression = "java(com.portfolioapp.taskamanagementapp.model.ProjectStatus.INITIATED)")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "tasks", ignore = true)
    Project toEntity(ProjectRequestDto dto, User user);

    List<ProjectResponseDto> toListResponseDto(List<Project> projects);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProjectFromDto(ProjectUpdateRequestDto dto, @MappingTarget Project project);
}
