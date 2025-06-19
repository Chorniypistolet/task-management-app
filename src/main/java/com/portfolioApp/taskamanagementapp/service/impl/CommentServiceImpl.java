package com.portfolioapp.taskamanagementapp.service.impl;

import com.portfolioapp.taskamanagementapp.dto.comment.CommentRequestDto;
import com.portfolioapp.taskamanagementapp.dto.comment.CommentResponseDto;
import com.portfolioapp.taskamanagementapp.dto.comment.CommentSearchParameters;
import com.portfolioapp.taskamanagementapp.exception.EntityNotFoundException;
import com.portfolioapp.taskamanagementapp.mapper.CommentMapper;
import com.portfolioapp.taskamanagementapp.model.Comment;
import com.portfolioapp.taskamanagementapp.model.Task;
import com.portfolioapp.taskamanagementapp.model.User;
import com.portfolioapp.taskamanagementapp.repository.comment.CommentRepository;
import com.portfolioapp.taskamanagementapp.repository.comment.CommentSpecificationBuilder;
import com.portfolioapp.taskamanagementapp.repository.task.TaskRepository;
import com.portfolioapp.taskamanagementapp.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final CommentMapper commentMapper;
    private final CommentSpecificationBuilder specificationBuilder;

    @Override
    public CommentResponseDto addComment(CommentRequestDto dto, User user) {
        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new EntityNotFoundException("Task with id "
                        + dto.getTaskId() + " not found"));
        Comment comment = commentMapper.toEntity(dto, user, task);
        commentRepository.save(comment);
        return commentMapper.toDto(comment);

    }

    @Override
    public List<CommentResponseDto> getCommentsByTask(Long taskId) {
        if (taskRepository.findById(taskId).isEmpty()) {
            throw new EntityNotFoundException("Task with id "
                    + taskId + " not found");
        }
        List<Comment> comments = commentRepository.findAllByTaskId(taskId);
        return commentMapper.toCommentsDtoList(comments);
    }

    @Override
    public List<CommentResponseDto> searchComments(CommentSearchParameters searchParameters,
                                                   Pageable pageable) {
        Specification<Comment> commentSpecification = specificationBuilder
                .build(searchParameters);
        List<CommentResponseDto> list = commentRepository.findAll(commentSpecification, pageable)
                .map(commentMapper::toDto)
                .toList();
        if (list.isEmpty()) {
            throw new EntityNotFoundException("Comments with parameters " + searchParameters
                    + " are not found");
        }
        return list;
    }
}
