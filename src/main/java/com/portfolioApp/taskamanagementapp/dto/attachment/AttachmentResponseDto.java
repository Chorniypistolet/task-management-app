package com.portfolioapp.taskamanagementapp.dto.attachment;

import com.portfolioapp.taskamanagementapp.dto.task.TaskShortResponseDto;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AttachmentResponseDto {
    private TaskShortResponseDto task;
    private String fileName;
    private LocalDateTime uploadDate;
}
