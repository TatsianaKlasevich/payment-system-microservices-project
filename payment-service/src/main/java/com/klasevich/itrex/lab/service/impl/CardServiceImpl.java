package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.exception.CardNotFoundException;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.repository.CardRepository;
import com.klasevich.itrex.lab.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;


    public Card getCardById(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() -> new CardNotFoundException("Unable to find " +
                "card with cardId: " + cardId));
    }

    public Long createCard(Card card) {
        return cardRepository.save(card).getUserId();
    }

    public Card updateCard(Card card) {

        return cardRepository.save(card);
    }

//    public void updateCard(Card card) {
//        cardRepository.save(card);
//    }

    public Card deleteCard(Long cardId) {
        Card deletedCard = getCardById(cardId);
        cardRepository.deleteById(cardId);
        return deletedCard;
    }

    public List<Card> getCardsByUserId(Long userId) {
        return cardRepository.getCardsByUserId(userId);
    }
}