package org.mapstruct.extensions.spring.converter;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.controller.dto.DepositRequestDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentRequestDTO;
import com.klasevich.itrex.lab.controller.dto.TransferRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import javax.annotation.Generated;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.extensions.spring.converter.ConversionServiceAdapterGenerator",
    date = "2021-11-29T18:29:57.665429900Z"
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

  public Transaction mapDepositRequestDTOToTransaction(final DepositRequestDTO source) {
    return conversionService.convert(source, Transaction.class);
  }

  public Transaction mapPaymentRequestDTOToTransaction(final PaymentRequestDTO source) {
    return conversionService.convert(source, Transaction.class);
  }

  public Transaction mapTransferRequestDTOToTransaction(final TransferRequestDTO source) {
    return conversionService.convert(source, Transaction.class);
  }
}
