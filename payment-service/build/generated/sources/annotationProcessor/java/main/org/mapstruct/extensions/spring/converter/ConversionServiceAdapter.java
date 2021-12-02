package org.mapstruct.extensions.spring.converter;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.controller.dto.DepositRequestDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentRequestDTO;
import com.klasevich.itrex.lab.controller.dto.TransactionResponseDTO;
import com.klasevich.itrex.lab.controller.dto.TransferRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import javax.annotation.Generated;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.extensions.spring.converter.ConversionServiceAdapterGenerator",
    date = "2021-12-02T13:04:31.504034800Z"
)
@Component
public class ConversionServiceAdapter {
  private final ConversionService conversionService;

  public ConversionServiceAdapter(@Lazy final ConversionService conversionService) {
    this.conversionService = conversionService;
  }

  public Card mapCardRequestDTOToCard(final CardRequestDTO source) {
    return conversionService.convert(source, Card.class);
  }

  public CardResponseDTO mapCardToCardResponseDTO(final Card source) {
    return conversionService.convert(source, CardResponseDTO.class);
  }

  public Transaction mapDepositRequestDTOToTransaction(final DepositRequestDTO source) {
    return conversionService.convert(source, Transaction.class);
  }

  public Transaction mapPaymentRequestDTOToTransaction(final PaymentRequestDTO source) {
    return conversionService.convert(source, Transaction.class);
  }

  public TransactionResponseDTO mapTransactionToTransactionResponseDTO(final Transaction source) {
    return conversionService.convert(source, TransactionResponseDTO.class);
  }

  public Transaction mapTransferRequestDTOToTransaction(final TransferRequestDTO source) {
    return conversionService.convert(source, Transaction.class);
  }
}
