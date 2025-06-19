package com.portfolioapp.taskamanagementapp.repository.comment.spec;

import com.portfolioapp.taskamanagementapp.model.Comment;
import com.portfolioapp.taskamanagementapp.model.Task;
import com.portfolioapp.taskamanagementapp.repository.SpecificationProvider;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentSpecificationProvider {

    @Component
    private static class TaskIdSpecification implements SpecificationProvider<Comment> {
        private static final String TASK_ID_KEY = "taskId";

        @Override
        public String getKey() {
            return TASK_ID_KEY;
        }

        @Override
        public Specification<Comment> getSpecification(String[] params) {
            List<Long> taskIds = Arrays.stream(params)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            return (root, query, cb) -> {
                Join<Comment, Task> taskJoin = root.join("task");
                return taskJoin.get("id").in(taskIds);
            };
        }
    }

    @Component
    private static class UserNameSpecification implements SpecificationProvider<Comment> {
        private static final String USERNAME_KEY = "userName";

        @Override
        public String getKey() {
            return USERNAME_KEY;
        }

        @Override
        public Specification<Comment> getSpecification(String[] params) {
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
    private static class TextSpecification implements SpecificationProvider<Comment> {
        private static final String TEXT_KEY = "text";

        @Override
        public String getKey() {
            return TEXT_KEY;
        }

        @Override
        public Specification<Comment> getSpecification(String[] params) {
            return (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                for (String param : params) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("text")),
                            "%" + param.toLowerCase() + "%"));
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            };
        }
    }
}
