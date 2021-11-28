package com.klasevich.itrex.lab.mapper;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-28T18:25:09+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class CardRequestDTOToCardMapperImpl implements CardRequestDTOToCardMapper {

    @Override
    public Card convert(CardRequestDTO source) {
        if ( source == null ) {
            return null;
        }

        Card card = new Card();

        card.setBalance( source.getBalance() );
        card.setCardNumber( source.getCardNumber() );
        card.setIsDefault( source.getIsDefault() );
        card.setCardStatus( source.getCardStatus() );
        card.setExpirationDate( source.getExpirationDate() );
        card.setUserId( source.getUserId() );

        return card;
    }
}
