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
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card getCardById(Integer cardId) {
        return cardRepository.findById(cardId).orElseThrow(() -> new CardNotFoundException("Unable to find " +
                "card with cardId: " + cardId));
    }

    public Integer createCard(BigDecimal balance, Currency currency, Long cardNumber, boolean isDefault,
                              CardStatus cardStatus, LocalDate expirationDate, Integer cvvCode, Integer accountId) {
        Card card = new Card(balance, currency, cardNumber, isDefault, cardStatus, expirationDate, cvvCode, accountId);
        return cardRepository.save(card).getCardId();
    }

    public Card updateCard(Integer cardId, BigDecimal balance, Currency currency, Long cardNumber, boolean isDefault,
                           CardStatus cardStatus, LocalDate expirationDate, Integer cvvCode, Integer accountId) {

        Card card = new Card(cardId, balance, currency, cardNumber, isDefault, cardStatus, expirationDate,
                cvvCode, accountId);
        return cardRepository.save(card);
    }

    public Card deleteCard(Integer cardId) {
        Card deletedCard = getCardById(cardId);
        cardRepository.deleteById(cardId);
        return deletedCard;
    }

    public List<Card> getCardsByUserId(int userId) {
        return cardRepository.getCardsByUserId(userId);
    }
}
