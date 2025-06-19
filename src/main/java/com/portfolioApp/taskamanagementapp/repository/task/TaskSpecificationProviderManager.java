package com.portfolioapp.taskamanagementapp.repository.task;

import com.portfolioapp.taskamanagementapp.model.Task;
import com.portfolioapp.taskamanagementapp.repository.SpecificationProvider;
import com.portfolioapp.taskamanagementapp.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskSpecificationProviderManager implements SpecificationProviderManager<Task> {
    private final List<SpecificationProvider<Task>> taskSpecificationProviders;

    @Override
    public SpecificationProvider<Task> getSpecificationProvider(String key) {
        return taskSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cant find correct"
                        + " specification provider for key " + key));
    }
}
