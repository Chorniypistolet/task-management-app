package com.portfolioapp.taskamanagementapp.repository.task.spec;

import com.portfolioapp.taskamanagementapp.model.Task;
import com.portfolioapp.taskamanagementapp.repository.SpecificationProvider;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskSpecificationProvider {

    @Component
    private static class NameSpecification implements SpecificationProvider<Task> {
        private static final String NAME_KEY = "name";

        @Override
        public String getKey() {
            return NAME_KEY;
        }

        @Override
        public Specification<Task> getSpecification(String[] params) {
            return (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                for (String param : params) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + param.toLowerCase() + "%"));
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            };
        }
    }

    @Component
    private static class PrioritySpecification implements SpecificationProvider<Task> {
        private static final String PRIORITY_KEY = "priority";

        @Override
        public String getKey() {
            return PRIORITY_KEY;
        }

        @Override
        public Specification<Task> getSpecification(String[] params) {
            return (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                for (String param : params) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("priority")),
                            "%" + param.toLowerCase() + "%"));
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            };
        }
    }

    @Component
    private static class StatusSpecification implements SpecificationProvider<Task> {
        private static final String STATUS_KEY = "status";

        @Override
        public String getKey() {
            return STATUS_KEY;
        }

        @Override
        public Specification<Task> getSpecification(String[] params) {
            return (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                for (String param : params) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("status")),
                            "%" + param.toLowerCase() + "%"));
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            };
        }
    }

    @Component
    private static class UserNameSpecification implements SpecificationProvider<Task> {
        private static final String USERNAME_KEY = "userName";

        @Override
        public String getKey() {
            return USERNAME_KEY;
        }

        @Override
        public Specification<Task> getSpecification(String[] params) {
            return (root, query, cb) -> {
                Join<Object, Object> userJoin = root.join("user");
                List<Predicate> predicates = new ArrayList<>();
                for (String param : params) {
                    predicates.add(cb.like(cb.lower(userJoin.get("userName")),
                            "%" + param.toLowerCase() + "%"));
                }
                return cb.or(predicates.toArray(new Predicate[0]));
            };
        }
    }

    @Component
    private static class LabelNameNameSpecification implements SpecificationProvider<Task> {
        private static final String LABELNAME_KEY = "labelName";

        @Override
        public String getKey() {
            return LABELNAME_KEY;
        }

        @Override
        public Specification<Task> getSpecification(String[] params) {
            return (root, query, cb) -> {
                Join<Object, Object> labelJoin = root.join("label");
                List<Predicate> predicates = new ArrayList<>();
                for (String param : params) {
                    predicates.add(cb.like(cb.lower(labelJoin.get("name")),
                            "%" + param.toLowerCase() + "%"));
                }
                return cb.or(predicates.toArray(new Predicate[0]));
            };
        }
    }
}
