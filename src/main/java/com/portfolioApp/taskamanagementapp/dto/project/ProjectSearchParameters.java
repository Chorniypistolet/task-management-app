package com.portfolioapp.taskamanagementapp.dto.project;

import com.portfolioapp.taskamanagementapp.dto.SearchParameters;
import lombok.Data;

@Data
public class ProjectSearchParameters implements SearchParameters {
    private String[] name;
    private String[] status;
    private String[] userName;
}
