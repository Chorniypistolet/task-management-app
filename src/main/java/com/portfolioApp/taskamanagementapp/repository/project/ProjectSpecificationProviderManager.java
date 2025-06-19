package com.portfolioapp.taskamanagementapp.repository.project;

import com.portfolioapp.taskamanagementapp.model.Project;
import com.portfolioapp.taskamanagementapp.repository.SpecificationProvider;
import com.portfolioapp.taskamanagementapp.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProjectSpecificationProviderManager implements SpecificationProviderManager<Project> {
    private final List<SpecificationProvider<Project>> projectSpecificationProviders;

    @Override
    public SpecificationProvider<Project> getSpecificationProvider(String key) {
        return projectSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cant find correct"
                        + " specification provider for key " + key));
    }
}
