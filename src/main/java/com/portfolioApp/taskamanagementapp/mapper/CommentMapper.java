package com.portfolioapp.taskamanagementapp.mapper;

import com.portfolioapp.taskamanagementapp.config.MapperConfig;
import com.portfolioapp.taskamanagementapp.dto.comment.CommentRequestDto;
import com.portfolioapp.taskamanagementapp.dto.comment.CommentResponseDto;
import com.portfolioapp.taskamanagementapp.model.Comment;
import com.portfolioapp.taskamanagementapp.model.Task;
import com.portfolioapp.taskamanagementapp.model.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timeStamp", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "task", source = "task")
    Comment toEntity(CommentRequestDto dto, User user, Task task);

    @Mapping(target = "taskId", expression = "java(comment.getTask().getId())")
    CommentResponseDto toDto(Comment comment);

    List<CommentResponseDto> toCommentsDtoList(List<Comment> comments);
}
