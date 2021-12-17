package com.klasevich.itrex.lab.persistance.repository;

import com.klasevich.itrex.lab.persistance.entity.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.klasevich.itrex.lab.util.TestData.createNewCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Test
    void getCardsByUserId_shouldFindRequiredCard() {
        //given
        Card firstCard = createNewCard();
        Card saveCard = cardRepository.save(firstCard);
        Long cardId = saveCard.getCardId();
        Long userId = saveCard.getUserId();

        //when
        List<Card> cards = cardRepository.getCardsByUserId(userId);

        //then
        assertTrue(cards.contains(saveCard));
        cardRepository.delete(saveCard);
    }

    @Test
    void createCard_getCardById_cardNumberShouldBeTheSame() {
        // given
        Card card = createNewCard();
        Card saveCard = cardRepository.save(card);
        Long id = saveCard.getCardId();
        String expected = saveCard.getCardNumber();

        // when
        Card resultCard = cardRepository.findById(id).get();
        String actual = resultCard.getCardNumber();

        // then
        assertEquals(actual, expected);
        cardRepository.deleteById(id);
    }

    @Test
    void updateCardAndCheck_changesShouldBeMade() {
        //given
        Card card = createNewCard();
        Card savedCard = cardRepository.save(card);
        card.setBalance(BigDecimal.valueOf(500));
        BigDecimal expected = BigDecimal.valueOf(500);

        // when
        cardRepository.save(card);
        BigDecimal actual = card.getBalance();

        //then
        assertThat(expected).isEqualTo(actual);
        cardRepository.delete(savedCard);
    }

    @Test
    void deleteCard_changesShouldBeMade() {
        //given
        Card card = createNewCard();
        Card savedCard = cardRepository.save(card);
        Long id = savedCard.getCardId();

        //when
        cardRepository.delete(savedCard);

        //then
        assertTrue(cardRepository.findById(id).isEmpty());
    }

    @Test
    void findAll_sizeShouldBeValid() {
        //given
        Card card = createNewCard();
        int expectedSize = cardRepository.findAll().size() + 1;

        //when
        cardRepository.save(card);
        int actualSize = cardRepository.findAll().size();

        //then
        assertEquals(expectedSize, actualSize);
        cardRepository.delete(card);
    }
}

