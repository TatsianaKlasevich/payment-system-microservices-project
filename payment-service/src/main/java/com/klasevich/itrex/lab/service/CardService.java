package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {

    Card getCardById(Long cardId);

    Card deleteCard(Long cardId);

    List<Card> getCardsByUserId(Long userId);

    Long createCard(CardRequestDTO cardRequestDTO);

    Card updateCard(Long cardId, CardRequestDTO cardRequestDTO);

    void updateCard(Card card);
}
