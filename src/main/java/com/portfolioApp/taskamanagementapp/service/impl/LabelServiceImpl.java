package com.portfolioapp.taskamanagementapp.service.impl;

import com.portfolioapp.taskamanagementapp.dto.label.CreateLabelRequestDto;
import com.portfolioapp.taskamanagementapp.dto.label.LabelResponseDto;
import com.portfolioapp.taskamanagementapp.dto.label.UpdateLabelsRequestDto;
import com.portfolioapp.taskamanagementapp.exception.EntityNotFoundException;
import com.portfolioapp.taskamanagementapp.mapper.LabelMapper;
import com.portfolioapp.taskamanagementapp.model.Label;
import com.portfolioapp.taskamanagementapp.repository.label.LabelRepository;
import com.portfolioapp.taskamanagementapp.service.LabelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;
    private final LabelMapper labelMapper;

    @Override
    public LabelResponseDto create(CreateLabelRequestDto requestDto) {
        Label label = labelMapper.toEntity(requestDto);
        labelRepository.save(label);
        return labelMapper.toDto(label);
    }

    @Override
    public List<LabelResponseDto> getLabels() {
        List<Label> labels = labelRepository.findAllActive();
        return labelMapper.labelsDtoList(labels);
    }

    @Override
    public LabelResponseDto update(Long id, UpdateLabelsRequestDto requestDto) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Label with id "
                + id + " is not found"));
        labelMapper.updateFromDto(requestDto, label);
        labelRepository.save(label);
        return labelMapper.toDto(label);
    }

    @Override
    public void deleteLabel(Long id) {
        labelRepository.deleteById(id);
    }
}
