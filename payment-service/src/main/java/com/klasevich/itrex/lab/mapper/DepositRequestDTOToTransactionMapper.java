package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.DepositRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface DepositRequestDTOToTransactionMapper extends Converter<DepositRequestDTO, Transaction> {
    @Override
    Transaction convert(DepositRequestDTO depositRequestDTO);
}

