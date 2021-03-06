package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.User;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface UserRequestDTOToUserMapper extends Converter<UserRequestDTO, User> {

    @Override
    User convert(UserRequestDTO source);
}
