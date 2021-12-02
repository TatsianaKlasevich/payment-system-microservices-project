package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.TransactionResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-02T16:04:30+0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class TransactionToTransactionResponseDTOMapperImpl implements TransactionToTransactionResponseDTOMapper {

    @Override
    public TransactionResponseDTO convert(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();

        transactionResponseDTO.setTransactionId( transaction.getTransactionId() );
        transactionResponseDTO.setUserId( transaction.getUserId() );
        transactionResponseDTO.setEmail( transaction.getEmail() );
        transactionResponseDTO.setAmount( transaction.getAmount() );
        transactionResponseDTO.setUnp( transaction.getUnp() );
        transactionResponseDTO.setPurposeOfPayment( transaction.getPurposeOfPayment() );
        transactionResponseDTO.setTransactionType( transaction.getTransactionType() );
        transactionResponseDTO.setBankCode( transaction.getBankCode() );
        transactionResponseDTO.setCreatAt( transaction.getCreatAt() );
        transactionResponseDTO.setUpdateAt( transaction.getUpdateAt() );

        return transactionResponseDTO;
    }
}
