package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.DepositRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-28T18:25:09+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class DepositRequestDTOToTransactionMapperImpl implements DepositRequestDTOToTransactionMapper {

    @Override
    public Transaction convert(DepositRequestDTO depositRequestDTO) {
        if ( depositRequestDTO == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setUserId( depositRequestDTO.getUserId() );
        transaction.setAmount( depositRequestDTO.getAmount() );

        return transaction;
    }
}
