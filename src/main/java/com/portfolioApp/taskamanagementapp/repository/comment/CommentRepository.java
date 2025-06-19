package com.portfolioapp.taskamanagementapp.repository.comment;

import com.portfolioapp.taskamanagementapp.model.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentRepository extends JpaRepository<Comment, Long>,
        JpaSpecificationExecutor<Comment> {

    List<Comment> findAllByTaskId(Long id);
}
