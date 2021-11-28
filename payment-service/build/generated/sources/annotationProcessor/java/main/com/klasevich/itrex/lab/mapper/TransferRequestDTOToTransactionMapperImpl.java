package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.TransferRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-28T18:25:17+0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class TransferRequestDTOToTransactionMapperImpl implements TransferRequestDTOToTransactionMapper {

    @Override
    public Transaction convert(TransferRequestDTO transferRequestDTO) {
        if ( transferRequestDTO == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setUserId( transferRequestDTO.getUserId() );
        transaction.setAmount( transferRequestDTO.getAmount() );
        transaction.setRecipientCardId( transferRequestDTO.getRecipientCardId() );

        return transaction;
    }
}
