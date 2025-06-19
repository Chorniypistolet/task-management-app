package com.portfolioapp.taskamanagementapp.dto.attachment;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AttachmentShortResponseDto {
    private String fileName;
    private LocalDateTime uploadDate;
}
