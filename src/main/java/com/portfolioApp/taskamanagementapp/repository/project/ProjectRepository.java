package com.portfolioapp.taskamanagementapp.repository.project;

import com.portfolioapp.taskamanagementapp.model.Project;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long>,
        JpaSpecificationExecutor<Project> {

    @Query("""
        SELECT DISTINCT p FROM Project p
        LEFT JOIN FETCH p.tasks t
        LEFT JOIN FETCH t.assignee
        LEFT JOIN FETCH p.user
        WHERE p.id = :id AND p.isDeleted = false
            """)
    Optional<Project> findByIdWithTasksAndAssignees(@Param("id") Long id);

    @Query("""
             SELECT DISTINCT p FROM Project p
             LEFT JOIN FETCH p.tasks t
             LEFT JOIN FETCH t.assignee
             WHERE p.user.id = :userId AND p.isDeleted = false
             """)
    List<Project> findAllByUserIdWithTasks(@Param("userId") Long userId);
}
