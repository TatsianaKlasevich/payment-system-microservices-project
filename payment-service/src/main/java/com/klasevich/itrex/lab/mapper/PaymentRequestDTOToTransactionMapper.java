package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.PaymentRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface PaymentRequestDTOToTransactionMapper extends Converter<PaymentRequestDTO, Transaction> {
    @Override
    Transaction convert(PaymentRequestDTO paymentRequestDTO);
}