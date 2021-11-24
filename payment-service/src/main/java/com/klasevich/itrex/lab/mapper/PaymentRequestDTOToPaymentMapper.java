package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.PaymentRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Payment;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface PaymentRequestDTOToPaymentMapper extends Converter<PaymentRequestDTO, Payment> {
    @Override
    Payment convert(PaymentRequestDTO paymentRequestDTO);
}