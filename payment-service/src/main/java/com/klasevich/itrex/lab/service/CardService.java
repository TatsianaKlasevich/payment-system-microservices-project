package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.persistance.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {

    Card getCardById(Long cardId);

    Card deleteCard(Long cardId);

    List<Card> getCardsByUserId(Long userId);

    Card createCard(Card card);

    Card updateCard(Card card);

    Page<Card> findAllCards(Pageable pageable);
}
