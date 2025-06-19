package com.portfolioapp.taskamanagementapp.service;

import com.portfolioapp.taskamanagementapp.dto.label.CreateLabelRequestDto;
import com.portfolioapp.taskamanagementapp.dto.label.LabelResponseDto;
import com.portfolioapp.taskamanagementapp.dto.label.UpdateLabelsRequestDto;
import java.util.List;

public interface LabelService {
    LabelResponseDto create(CreateLabelRequestDto requestDto);

    List<LabelResponseDto> getLabels();

    LabelResponseDto update(Long id, UpdateLabelsRequestDto requestDto);

    void deleteLabel(Long id);
}
