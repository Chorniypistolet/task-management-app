package com.portfolioapp.taskamanagementapp.repository.project;

import com.portfolioapp.taskamanagementapp.dto.project.ProjectSearchParameters;
import com.portfolioapp.taskamanagementapp.model.Project;
import com.portfolioapp.taskamanagementapp.repository.SpecificationBuilder;
import com.portfolioapp.taskamanagementapp.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProjectSpecificationBuilder implements SpecificationBuilder<Project,
        ProjectSearchParameters> {
    private final SpecificationProviderManager<Project> specificationProviderManager;

    @Override
    public Specification<Project> build(ProjectSearchParameters parameters) {
        Specification<Project> projectSpecification = Specification.where(null);
        if (parameters.getName() != null && parameters.getName().length > 0) {
            projectSpecification = projectSpecification.and(specificationProviderManager
                    .getSpecificationProvider("name")
                    .getSpecification(parameters.getName()));
        }
        if (parameters.getStatus() != null && parameters.getStatus().length > 0) {
            projectSpecification = projectSpecification.and(specificationProviderManager
                    .getSpecificationProvider("status")
                    .getSpecification(parameters.getStatus()));
        }
        if (parameters.getUserName() != null && parameters.getUserName().length > 0) {
            projectSpecification = projectSpecification.and(specificationProviderManager
                    .getSpecificationProvider("userName")
                    .getSpecification(parameters.getUserName()));
        }
        return projectSpecification;
    }
}
