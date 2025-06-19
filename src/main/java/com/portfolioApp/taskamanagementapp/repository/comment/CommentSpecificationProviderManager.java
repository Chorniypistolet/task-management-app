package com.portfolioapp.taskamanagementapp.repository.comment;

import com.portfolioapp.taskamanagementapp.model.Comment;
import com.portfolioapp.taskamanagementapp.repository.SpecificationProvider;
import com.portfolioapp.taskamanagementapp.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentSpecificationProviderManager implements SpecificationProviderManager<Comment> {
    private final List<SpecificationProvider<Comment>> commentSpecificationProviders;

    @Override
    public SpecificationProvider<Comment> getSpecificationProvider(String key) {
        return commentSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cant find correct"
                        + " specification provider for key " + key));
    }
}
