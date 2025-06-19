package com.portfolioapp.taskamanagementapp.repository;

import com.portfolioapp.taskamanagementapp.dto.SearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T, P extends SearchParameters> {
    Specification<T> build(P parameters);
}
