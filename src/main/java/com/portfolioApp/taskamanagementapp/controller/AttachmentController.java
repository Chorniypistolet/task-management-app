package com.portfolioapp.taskamanagementapp.controller;

import com.portfolioapp.taskamanagementapp.dto.attachment.AttachmentDeleteRequestDto;
import com.portfolioapp.taskamanagementapp.dto.attachment.AttachmentResponseDto;
import com.portfolioapp.taskamanagementapp.dto.attachment.AttachmentShortResponseDto;
import com.portfolioapp.taskamanagementapp.service.AttachmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attachments")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping
    public AttachmentResponseDto uploadAttachment(
            @RequestParam("taskId") Long taskId,
            @RequestParam("file") MultipartFile file) {
        return attachmentService.uploadAttachment(taskId, file);
    }

    @GetMapping
    public List<AttachmentShortResponseDto> getAttachments(@RequestParam("taskId") Long taskId) {
        return attachmentService.getAttachmentsByTaskId(taskId);
    }

    @DeleteMapping
    public void deleteAttachment(@RequestBody AttachmentDeleteRequestDto requestDto) {
        attachmentService.deleteAttachment(requestDto.getDropBoxFileId());
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String dropBoxFileId) {
        return attachmentService.downloadFile(dropBoxFileId);
    }
}
