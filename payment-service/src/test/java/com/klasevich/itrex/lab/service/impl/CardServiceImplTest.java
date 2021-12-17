package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.exception.CardNotFoundException;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.klasevich.itrex.lab.util.TestData.createNewCard;
import static com.klasevich.itrex.lab.util.TestData.createSecondCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {
    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    @Test
    void getCardById_cardNumberShouldBeTheSame() {
        // given
        Card card = createNewCard();
        Long cardId = 1L;
        card.setCardId(cardId);

        // when
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        Card resultCard = cardService.getCardById(cardId);

        // then
        assertThat(resultCard.getCardNumber()).isEqualTo(card.getCardNumber());
    }

    @Test
    void cardNotFoundException_whenCardGetByIdNotExist() {
        //given
        Long id = 1L;
        String message = "Unable to find card with cardId: " + id;

        // when
        when(cardRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        CardNotFoundException exception = assertThrows(CardNotFoundException.class,
                () -> {
                    cardService.getCardById(id);
                });

        // then
        assertThat(message).isEqualTo(exception.getMessage());
    }

    @Test
    void findCardsByPage_shouldReturnValidNumberOfCards() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(createNewCard());
        cards.add(createSecondCard());
        Page<Card> page = new PageImpl<>((cards));
        Pageable pageable = PageRequest.of(1, cards.size());

        // when
        when(cardRepository.findAll(pageable)).thenReturn(page);
        List<Card> result = cardService.findAllCards(pageable).getContent();

        //then
        assertThat(result.size()).isEqualTo(cards.size());
    }

    @Test
    void updateCardAndCheck_changesShouldBeMade() {
        //given
        Card card = createNewCard();
        card.setBalance(BigDecimal.valueOf(2500));

        // when
        when(cardRepository.save(card)).thenReturn(card);
        Card updatedCard = cardService.updateCard(card);

        //then
        assertThat(updatedCard.getBalance()).isEqualTo(card.getBalance());
    }

    @Test
    void createCardAndCheck_CardShouldBeAdded() {
        //given
        Card card = createNewCard();

        // when
        when(cardRepository.save(card)).thenReturn(card);
        Card updatedCard = cardService.updateCard(card);

        //then
        assertThat(updatedCard.getCardNumber()).isEqualTo(card.getCardNumber());
    }

    @Test
    void getCardByUserId_cardNumberShouldBeTheSame() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(createNewCard());
        cards.add(createSecondCard());
        Long userId = 1L;

        // when
        when(cardRepository.getCardsByUserId(ArgumentMatchers.anyLong())).thenReturn(cards);
        List<Card> resultList = cardService.getCardsByUserId(userId);

        // then
        assertThat(resultList.size()).isEqualTo(cards.size());
    }
}