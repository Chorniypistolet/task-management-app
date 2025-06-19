package com.portfolioapp.taskamanagementapp.service;

import com.portfolioapp.taskamanagementapp.dto.attachment.AttachmentResponseDto;
import com.portfolioapp.taskamanagementapp.dto.attachment.AttachmentShortResponseDto;
import java.util.List;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    AttachmentResponseDto uploadAttachment(Long taskId, MultipartFile file);

    List<AttachmentShortResponseDto> getAttachmentsByTaskId(Long taskId);

    void deleteAttachment(String dropBoxFileId);

    ResponseEntity<InputStreamResource> downloadFile(String dropBoxFileId);
}
