package com.portfolioapp.taskamanagementapp.dto.comment;

import com.portfolioapp.taskamanagementapp.dto.SearchParameters;
import lombok.Data;

@Data
public class CommentSearchParameters implements SearchParameters {
    private String[] taskId;
    private String[] userName;
    private String[] text;
}
