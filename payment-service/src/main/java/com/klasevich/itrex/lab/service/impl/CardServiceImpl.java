package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.exception.CardNotFoundException;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.repository.CardRepository;
import com.klasevich.itrex.lab.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.klasevich.itrex.lab.util.ServiceData.CARD_EXCEPTION_MESSAGE;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public Card getCardById(Long cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException(String.format(CARD_EXCEPTION_MESSAGE, cardId)));
    }

    @Override
    @Transactional
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    @Transactional
    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    @Transactional
    public Card deleteCard(Long cardId) {
        Card deletedCard = getCardById(cardId);
        cardRepository.deleteById(cardId);
        return deletedCard;
    }

    @Override
    public List<Card> getCardsByUserId(Long userId) {
        return cardRepository.getCardsByUserId(userId);
    }

    @Override
    public Page<Card> findAllCards(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }
}
