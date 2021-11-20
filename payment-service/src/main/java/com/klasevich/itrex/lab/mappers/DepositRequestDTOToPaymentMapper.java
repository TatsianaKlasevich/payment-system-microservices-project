package com.klasevich.itrex.lab.mappers;

import com.klasevich.itrex.lab.controller.dto.DepositRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Payment;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface DepositRequestDTOToPaymentMapper extends Converter<DepositRequestDTO, Payment> {

    @Override
    Payment convert(DepositRequestDTO source);
}
