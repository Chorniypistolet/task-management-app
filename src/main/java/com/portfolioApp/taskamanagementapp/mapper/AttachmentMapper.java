package com.portfolioapp.taskamanagementapp.mapper;

import com.portfolioapp.taskamanagementapp.config.MapperConfig;
import com.portfolioapp.taskamanagementapp.dto.attachment.AttachmentResponseDto;
import com.portfolioapp.taskamanagementapp.dto.attachment.AttachmentShortResponseDto;
import com.portfolioapp.taskamanagementapp.model.Attachment;
import com.portfolioapp.taskamanagementapp.model.Task;
import java.time.LocalDateTime;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

@Mapper(config = MapperConfig.class)
public interface AttachmentMapper {
    List<AttachmentShortResponseDto> toDtoList(List<Attachment> attachments);

    AttachmentResponseDto toDto(Attachment attachment);

    @Named("toEntity")
    default Attachment toEntity(Task task, MultipartFile file,
                                String dropboxFileId, String dropboxPath) {
        Attachment attachment = new Attachment();
        attachment.setTask(task);
        attachment.setFileName(file.getOriginalFilename());
        attachment.setDropBoxFileId(dropboxFileId);
        attachment.setUploadDate(LocalDateTime.now());
        attachment.setDropboxPath(dropboxPath);
        return attachment;
    }
}
