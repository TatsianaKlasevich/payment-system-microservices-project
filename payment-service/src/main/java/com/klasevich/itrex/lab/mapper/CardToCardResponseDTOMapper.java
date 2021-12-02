package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface CardToCardResponseDTOMapper extends Converter<Card, CardResponseDTO> {

    @Override
    CardResponseDTO convert(Card card);
}
