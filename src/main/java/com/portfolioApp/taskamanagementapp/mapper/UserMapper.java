package com.portfolioapp.taskamanagementapp.mapper;

import com.portfolioapp.taskamanagementapp.config.MapperConfig;
import com.portfolioapp.taskamanagementapp.dto.user.UserRegistrationRequestDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserResponseDto;
import com.portfolioapp.taskamanagementapp.dto.user.UserUpdateRequestDto;
import com.portfolioapp.taskamanagementapp.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    @Mapping(source = "userName", target = "userName")
    UserResponseDto toUserResponse(User user);

    @Mapping(source = "userName", target = "userName")
    User toUser(UserRegistrationRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserUpdateRequestDto dto, @MappingTarget User user);
}
