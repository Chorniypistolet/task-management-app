package com.portfolioapp.taskamanagementapp.repository.attachment;

import com.portfolioapp.taskamanagementapp.model.Attachment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findAllByTaskId(Long taskId);

    void delete(Attachment attachment);

    Optional<Attachment> getAttachmentByDropBoxFileId(String dropBoxFileId);
}
