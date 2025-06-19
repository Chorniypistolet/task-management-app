package com.portfolioapp.taskamanagementapp.controller;

import com.portfolioapp.taskamanagementapp.dto.comment.CommentRequestDto;
import com.portfolioapp.taskamanagementapp.dto.comment.CommentResponseDto;
import com.portfolioapp.taskamanagementapp.dto.comment.CommentSearchParameters;
import com.portfolioapp.taskamanagementapp.model.User;
import com.portfolioapp.taskamanagementapp.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal User user) {
        return commentService.addComment(requestDto, user);
    }

    @GetMapping
    public List<CommentResponseDto> getCommentsByTaskId(@RequestParam Long taskId) {
        return commentService.getCommentsByTask(taskId);
    }

    @GetMapping("search")
    public List<CommentResponseDto> searchCommentByParams(CommentSearchParameters searchParameters,
                                                          @ParameterObject
                                                          @PageableDefault Pageable pageable) {
        return commentService.searchComments(searchParameters, pageable);
    }
}
