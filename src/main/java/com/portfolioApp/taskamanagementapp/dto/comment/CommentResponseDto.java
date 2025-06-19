package com.portfolioapp.taskamanagementapp.dto.comment;

import com.portfolioapp.taskamanagementapp.dto.user.UserShortResponseDto;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommentResponseDto {
    private Long taskId;
    private UserShortResponseDto user;
    private String text;
    private LocalDateTime timeStamp;
}
