package com.portfolioapp.taskamanagementapp.controller;

import com.portfolioapp.taskamanagementapp.dto.label.CreateLabelRequestDto;
import com.portfolioapp.taskamanagementapp.dto.label.LabelResponseDto;
import com.portfolioapp.taskamanagementapp.dto.label.UpdateLabelsRequestDto;
import com.portfolioapp.taskamanagementapp.service.LabelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;

    @PostMapping
    LabelResponseDto createLabel(@RequestBody CreateLabelRequestDto requestDto) {
        return labelService.create(requestDto);
    }

    @GetMapping
    List<LabelResponseDto> getAllLabels() {
        return labelService.getLabels();
    }

    @PutMapping("/{id}")
    LabelResponseDto updateLabel(@PathVariable Long id,
                                 @RequestBody UpdateLabelsRequestDto requestDto) {
        return labelService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    void deleteLabel(@PathVariable Long id) {
        labelService.deleteLabel(id);
    }
}
