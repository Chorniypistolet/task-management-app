package com.portfolioapp.taskamanagementapp.service;

import com.portfolioapp.taskamanagementapp.dto.comment.CommentRequestDto;
import com.portfolioapp.taskamanagementapp.dto.comment.CommentResponseDto;
import com.portfolioapp.taskamanagementapp.dto.comment.CommentSearchParameters;
import com.portfolioapp.taskamanagementapp.model.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentResponseDto addComment(CommentRequestDto dto, User user);

    List<CommentResponseDto> getCommentsByTask(Long taskId);

    List<CommentResponseDto> searchComments(CommentSearchParameters searchParameters,
                                            Pageable pageable);
}
