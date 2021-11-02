package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.mappers.CardRequestDTOToCardMapper;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.CardStatus;
import com.klasevich.itrex.lab.persistance.entity.Currency;
import com.klasevich.itrex.lab.exception.CardNotFoundException;
import com.klasevich.itrex.lab.persistance.repository.CardRepository;
import com.klasevich.itrex.lab.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardRequestDTOToCardMapper cardRequestDTOToCardMapper;

    public Card getCardById(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() -> new CardNotFoundException("Unable to find " +
                "card with cardId: " + cardId));
    }

    public Long createCard(CardRequestDTO cardRequestDTO) {
        Card card =cardRequestDTOToCardMapper.convert(cardRequestDTO);
        return cardRepository.save(card).getUserId();
    }

    public Card updateCard(Long cardId, CardRequestDTO cardRequestDTO) {
        Card card = cardRequestDTOToCardMapper.convert(cardRequestDTO);
        card.setUserId(cardId);
        return cardRepository.save(card);
    }

    public void updateCard(Card card){
        cardRepository.save(card);
    }

    public Card deleteCard(Long cardId) {
        Card deletedCard = getCardById(cardId);
        cardRepository.deleteById(cardId);
        return deletedCard;
    }

    public List<Card> getCardsByUserId(Long userId) {
        return cardRepository.getCardsByUserId(userId);
    }
}