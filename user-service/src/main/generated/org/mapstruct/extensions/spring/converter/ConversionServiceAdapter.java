package org.mapstruct.extensions.spring.converter;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.User;

import javax.annotation.Generated;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.extensions.spring.converter.ConversionServiceAdapterGenerator",
        date = "2021-11-17T11:48:52.589835300Z"
)
@Component
public class ConversionServiceAdapter {
    private final ConversionService conversionService;

    public ConversionServiceAdapter(@Lazy final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public User mapUserRequestDTOToUser(final UserRequestDTO source) {
        return conversionService.convert(source, User.class);
    }
}
