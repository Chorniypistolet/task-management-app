package com.portfolioapp.taskamanagementapp.dto.task;

import com.portfolioapp.taskamanagementapp.dto.SearchParameters;
import lombok.Data;

@Data
public class TaskSearchParameters implements SearchParameters {
    private String[] name;
    private String[] priority;
    private String[] status;
    private String[] userName;
    private String[] labelName;
}
