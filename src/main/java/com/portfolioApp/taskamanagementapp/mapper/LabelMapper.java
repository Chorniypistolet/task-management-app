package com.portfolioapp.taskamanagementapp.mapper;

import com.portfolioapp.taskamanagementapp.config.MapperConfig;
import com.portfolioapp.taskamanagementapp.dto.label.CreateLabelRequestDto;
import com.portfolioapp.taskamanagementapp.dto.label.LabelResponseDto;
import com.portfolioapp.taskamanagementapp.dto.label.UpdateLabelsRequestDto;
import com.portfolioapp.taskamanagementapp.model.Label;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class)
public interface LabelMapper {

    Label toEntity(CreateLabelRequestDto requestDto);

    LabelResponseDto toDto(Label label);

    List<LabelResponseDto> labelsDtoList(List<Label> labels);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UpdateLabelsRequestDto dto, @MappingTarget Label label);
}
