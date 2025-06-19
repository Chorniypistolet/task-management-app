package com.portfolioapp.taskamanagementapp.repository.task;

import com.portfolioapp.taskamanagementapp.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long>,
        JpaSpecificationExecutor<Task> {
    @Query("SELECT t FROM Task t WHERE t.isDeleted = false")
    List<Task> findAllActive();
}
