package com.portfolioapp.taskamanagementapp.repository.label;

import com.portfolioapp.taskamanagementapp.model.Label;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LabelRepository extends JpaRepository<Label, Long> {

    @Query("SELECT l FROM Label l WHERE l.isDeleted = false")
    List<Label> findAllActive();
}
