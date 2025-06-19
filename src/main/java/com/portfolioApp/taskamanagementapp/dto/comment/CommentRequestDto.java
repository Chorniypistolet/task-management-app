package com.portfolioapp.taskamanagementapp.dto.comment;

import lombok.Data;

@Data
public class CommentRequestDto {
    private Long taskId;
    private String text;
}
