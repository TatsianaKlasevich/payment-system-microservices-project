package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface CardRequestDTOToCardMapper extends Converter<CardRequestDTO, Card> {

    @Override
    Card convert(CardRequestDTO source);
}

