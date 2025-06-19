package com.portfolioapp.taskamanagementapp.repository.role;

import com.portfolioapp.taskamanagementapp.model.Role;
import com.portfolioapp.taskamanagementapp.model.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
