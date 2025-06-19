package com.portfolioapp.taskamanagementapp.repository.task;

import com.portfolioapp.taskamanagementapp.dto.task.TaskSearchParameters;
import com.portfolioapp.taskamanagementapp.model.Task;
import com.portfolioapp.taskamanagementapp.repository.SpecificationBuilder;
import com.portfolioapp.taskamanagementapp.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskSpecificationBuilder implements SpecificationBuilder<Task, TaskSearchParameters> {
    private final SpecificationProviderManager<Task> specificationProviderManager;

    @Override
    public Specification<Task> build(TaskSearchParameters parameters) {
        Specification<Task> taskSpecification = Specification.where(null);
        if (parameters.getName() != null && parameters.getName().length > 0) {
            taskSpecification = taskSpecification.and(specificationProviderManager
                    .getSpecificationProvider("name")
                    .getSpecification(parameters.getName()));

        }
        if (parameters.getPriority() != null && parameters.getPriority().length > 0) {
            taskSpecification = taskSpecification.and(specificationProviderManager
                    .getSpecificationProvider("priority")
                    .getSpecification(parameters.getName()));

        }
        if (parameters.getStatus() != null && parameters.getStatus().length > 0) {
            taskSpecification = taskSpecification.and(specificationProviderManager
                    .getSpecificationProvider("status")
                    .getSpecification(parameters.getName()));

        }
        if (parameters.getUserName() != null && parameters.getUserName().length > 0) {
            taskSpecification = taskSpecification.and(specificationProviderManager
                    .getSpecificationProvider("userName")
                    .getSpecification(parameters.getName()));

        }
        if (parameters.getLabelName() != null && parameters.getLabelName().length > 0) {
            taskSpecification = taskSpecification.and(specificationProviderManager
                    .getSpecificationProvider("label")
                    .getSpecification(parameters.getName()));

        }
        return taskSpecification;
    }
}
