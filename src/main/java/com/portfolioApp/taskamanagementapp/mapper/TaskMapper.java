package com.portfolioapp.taskamanagementapp.mapper;

import com.portfolioapp.taskamanagementapp.config.MapperConfig;
import com.portfolioapp.taskamanagementapp.dto.task.TaskRequestDto;
import com.portfolioapp.taskamanagementapp.dto.task.TaskResponseDto;
import com.portfolioapp.taskamanagementapp.dto.task.TaskUpdateRequestDto;
import com.portfolioapp.taskamanagementapp.model.Task;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class, uses = { LabelMapper.class })
public interface TaskMapper {

    @Mapping(target = "status",
            expression = "java(com.portfolioapp.taskamanagementapp.model.TaskStatus.NOT_STARTED)")
    Task toEntity(TaskRequestDto requestDto);

    TaskResponseDto toDto(Task task);

    List<TaskResponseDto> toTaskDtosList(List<Task> taskList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(TaskUpdateRequestDto dto, @MappingTarget Task task);
}
