package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.TransactionResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface TransactionToTransactionResponseDTOMapper extends Converter<Transaction, TransactionResponseDTO> {

    @Override
    TransactionResponseDTO convert(Transaction transaction);
}

