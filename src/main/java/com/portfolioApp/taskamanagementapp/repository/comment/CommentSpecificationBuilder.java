package com.portfolioapp.taskamanagementapp.repository.comment;

import com.portfolioapp.taskamanagementapp.dto.comment.CommentSearchParameters;
import com.portfolioapp.taskamanagementapp.model.Comment;
import com.portfolioapp.taskamanagementapp.repository.SpecificationBuilder;
import com.portfolioapp.taskamanagementapp.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentSpecificationBuilder implements SpecificationBuilder<Comment,
        CommentSearchParameters> {
    private final SpecificationProviderManager<Comment> specificationProviderManager;

    @Override
    public Specification<Comment> build(CommentSearchParameters parameters) {
        Specification<Comment> commentSpecification = Specification.where(null);
        if (parameters.getTaskId() != null && parameters.getTaskId().length > 0) {
            commentSpecification = commentSpecification.and(specificationProviderManager
                    .getSpecificationProvider("taskId")
                    .getSpecification(parameters.getTaskId()));

        }
        if (parameters.getUserName() != null && parameters.getUserName().length > 0) {
            commentSpecification = commentSpecification.and(specificationProviderManager
                    .getSpecificationProvider("userName")
                    .getSpecification(parameters.getUserName()));

        }
        if (parameters.getText() != null && parameters.getText().length > 0) {
            commentSpecification = commentSpecification.and(specificationProviderManager
                    .getSpecificationProvider("text")
                    .getSpecification(parameters.getText()));

        }
        return commentSpecification;
    }
}
