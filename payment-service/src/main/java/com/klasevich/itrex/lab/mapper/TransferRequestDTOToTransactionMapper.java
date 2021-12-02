package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.TransferRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface TransferRequestDTOToTransactionMapper extends Converter<TransferRequestDTO, Transaction> {

    @Override
    Transaction convert(TransferRequestDTO transferRequestDTO);
}