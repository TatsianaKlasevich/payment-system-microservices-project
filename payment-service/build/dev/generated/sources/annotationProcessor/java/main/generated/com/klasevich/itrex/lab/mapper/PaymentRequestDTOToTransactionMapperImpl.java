package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.PaymentRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-03T12:29:53+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class PaymentRequestDTOToTransactionMapperImpl implements PaymentRequestDTOToTransactionMapper {

    @Override
    public Transaction convert(PaymentRequestDTO paymentRequestDTO) {
        if ( paymentRequestDTO == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setUserId( paymentRequestDTO.getUserId() );
        transaction.setAmount( paymentRequestDTO.getAmount() );
        transaction.setUnp( paymentRequestDTO.getUnp() );
        transaction.setPurposeOfPayment( paymentRequestDTO.getPurposeOfPayment() );
        transaction.setBankCode( paymentRequestDTO.getBankCode() );

        return transaction;
    }
}
