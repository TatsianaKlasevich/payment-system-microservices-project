package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.UserResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.User;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface UserToUserResponseDTOMapper extends Converter<User, UserResponseDTO> {

    @Override
    UserResponseDTO convert(User user);
}

