package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.entity.Card;
import com.klasevich.itrex.lab.entity.CardStatus;
import com.klasevich.itrex.lab.entity.Currency;
import com.klasevich.itrex.lab.exception.CardNotFoundException;
import com.klasevich.itrex.lab.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card getCardById(Integer id) {
        return cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("Unable to find " +
                "card with id: " + id));
    }

    public Integer createCard(BigDecimal balance, Currency currency, Long cardNumber,
                              CardStatus cardStatus, LocalDate expirationDate, Integer cvvCode, Integer accountId) {
        Card card = new Card(balance, currency, cardNumber, cardStatus, expirationDate, cvvCode, accountId);
        return cardRepository.save(card).getId();
    }

    public Card updateCard(Integer id, BigDecimal balance, Currency currency, Long cardNumber,
                           CardStatus cardStatus, LocalDate expirationDate, Integer cvvCode, Integer accountId) {

        Card card = new Card(id, balance, currency, cardNumber, cardStatus, expirationDate, cvvCode, accountId);
        return cardRepository.save(card);
    }

    public Card deleteCard(Integer id) {
        Card deletedCard = getCardById(id);
        cardRepository.deleteById(id);
        return deletedCard;
    }
}
