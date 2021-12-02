package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.CardStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-02T16:04:31+0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class CardToCardResponseDTOMapperImpl implements CardToCardResponseDTOMapper {

    @Override
    public CardResponseDTO convert(Card card) {
        if ( card == null ) {
            return null;
        }

        Long arg0 = null;
        BigDecimal arg1 = null;
        Boolean arg2 = null;
        String arg3 = null;
        CardStatus arg4 = null;
        LocalDate arg5 = null;
        Long arg6 = null;

        CardResponseDTO cardResponseDTO = new CardResponseDTO( arg0, arg1, arg2, arg3, arg4, arg5, arg6 );

        cardResponseDTO.setCardId( card.getCardId() );
        cardResponseDTO.setBalance( card.getBalance() );
        cardResponseDTO.setIsDefault( card.getIsDefault() );
        cardResponseDTO.setCardNumber( card.getCardNumber() );
        cardResponseDTO.setCardStatus( card.getCardStatus() );
        cardResponseDTO.setExpirationDate( card.getExpirationDate() );
        cardResponseDTO.setUserId( card.getUserId() );

        return cardResponseDTO;
    }
}
