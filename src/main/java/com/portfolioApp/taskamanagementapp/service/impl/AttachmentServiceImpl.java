package com.portfolioapp.taskamanagementapp.service.impl;

import com.dropbox.core.v2.files.FileMetadata;
import com.portfolioapp.taskamanagementapp.dropbox.DropboxService;
import com.portfolioapp.taskamanagementapp.dto.attachment.AttachmentResponseDto;
import com.portfolioapp.taskamanagementapp.dto.attachment.AttachmentShortResponseDto;
import com.portfolioapp.taskamanagementapp.exception.EntityNotFoundException;
import com.portfolioapp.taskamanagementapp.mapper.AttachmentMapper;
import com.portfolioapp.taskamanagementapp.model.Attachment;
import com.portfolioapp.taskamanagementapp.model.Task;
import com.portfolioapp.taskamanagementapp.repository.attachment.AttachmentRepository;
import com.portfolioapp.taskamanagementapp.repository.task.TaskRepository;
import com.portfolioapp.taskamanagementapp.service.AttachmentService;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final DropboxService dropboxService;
    private final AttachmentRepository attachmentRepository;
    private final TaskRepository taskRepository;
    private final AttachmentMapper attachmentMapper;

    @Override
    @Transactional
    public AttachmentResponseDto uploadAttachment(Long taskId, MultipartFile file) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with id "
                        + taskId + " is not found"));
        String dropboxPath = "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        try (InputStream inputStream = file.getInputStream()) {
            FileMetadata metadata = dropboxService.uploadFile(dropboxPath, inputStream);
            Attachment attachment = attachmentMapper.toEntity(task, file,
                    metadata.getId(), dropboxPath);
            Attachment savedAttachment = attachmentRepository.save(attachment);
            return attachmentMapper.toDto(savedAttachment);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to Dropbox", e);
        }
    }

    @Override
    public List<AttachmentShortResponseDto> getAttachmentsByTaskId(Long taskId) {
        return attachmentMapper.toDtoList(attachmentRepository.findAllByTaskId(taskId));
    }

    @Override
    public void deleteAttachment(String dropBoxFileId) {
        Attachment attachment = attachmentRepository.getAttachmentByDropBoxFileId(dropBoxFileId)
                .orElseThrow(() -> new EntityNotFoundException("Attachment is not found"));
        try {
            dropboxService.deleteFile(attachment.getDropboxPath());
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file from Dropbox: "
                    + attachment.getDropBoxFileId(), e);
        }
        attachmentRepository.delete(attachment);
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadFile(String dropBoxFileId) {
        Attachment attachment = attachmentRepository.getAttachmentByDropBoxFileId(dropBoxFileId)
                .orElseThrow(() -> new EntityNotFoundException("Attachment is not found"));
        InputStream fileStream = dropboxService.downloadFile(attachment.getDropboxPath());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(fileStream));
    }
}
