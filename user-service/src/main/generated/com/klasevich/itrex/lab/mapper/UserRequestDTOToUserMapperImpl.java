package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.User;

import javax.annotation.processing.Generated;

import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-11-28T19:11:47+0300",
        comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class UserRequestDTOToUserMapperImpl implements UserRequestDTOToUserMapper {

    @Override
    public User convert(UserRequestDTO source) {
        if (source == null) {
            return null;
        }

        User user = new User();

        user.setEmail(source.getEmail());
        user.setName(source.getName());
        user.setSecondName(source.getSecondName());
        user.setSurname(source.getSurname());
        user.setDateOfBirth(source.getDateOfBirth());
        user.setIdentityPassportNumber(source.getIdentityPassportNumber());
        user.setPhoneNumber(source.getPhoneNumber());

        return user;
    }
}
