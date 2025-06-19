package com.portfolioapp.taskamanagementapp.dto.role;

import com.portfolioapp.taskamanagementapp.model.RoleName;
import lombok.Data;

@Data
public class RoleRequestDto {
    private String roleName;

    public RoleName getRoleName() {
        return RoleName.valueOf("ROLE_" + roleName.toUpperCase());
    }
}
