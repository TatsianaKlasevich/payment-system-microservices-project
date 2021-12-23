package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static com.klasevich.itrex.lab.util.TestData.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CardControllerTest extends BaseControllerTest {

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "create_card")
    void createCard_whenValidCardData_thenStatus200AndValidUserReturn() throws Exception {
        //given
        Card card = createNewCard();
        CardRequestDTO cardRequestDTO = createCardRequestDTO();

        //when
        when(cardService.createCard(any())).thenReturn(card);

        //then
        mockMvc.perform(post("/v1/cards/")
                        .content(objectMapper.writeValueAsString(cardRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardId").value(card.getCardId()))
                .andExpect(jsonPath("$.cardNumber").value(card.getCardNumber()));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "read_card")
    void getCardById_whenValidCardData_thenStatus200AndValidCardReturn() throws Exception {
        //given
        Card card = createNewCard();
        Long cardId = 1L;

        //when
        when(cardService.getCardById(anyLong())).thenReturn(card);

        //then
        mockMvc.perform(get("/v1/cards/{cardId}", cardId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardId").value(card.getCardId()));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "update_card")
    void updateCard_whenValidCardData_thenStatus200AndValidCardReturn() throws Exception {
        //given
        Card card = createNewCard();
        CardRequestDTO cardRequestDTO = createCardRequestDTO();
        Long cardId = 1L;

        //when
        when(cardService.updateCard(any())).thenReturn(card);
        when(cardRequestDTOToCardMapper.convert(any())).thenReturn(card);

        //then
        mockMvc.perform(put("/v1/cards/{cardId}", cardId)
                        .content(objectMapper.writeValueAsString(cardRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardId").value(card.getCardId()))
                .andExpect(jsonPath("$.cardNumber").value(card.getCardNumber()));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "delete")
    void deleteCard_whenCardDeletedSuccessfully_thenStatus200Return() throws Exception {
        //given
        Card card = createNewCard();
        Long cardId = 1L;

        //when
        when(cardService.deleteCard(any())).thenReturn(card);

        //then
        mockMvc.perform(delete("/v1/cards/{cardId}", cardId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardId").value(card.getCardId()))
                .andExpect(jsonPath("$.cardNumber").value(card.getCardNumber()));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "read_all")
    void findAllCardsBySomePage_whenGetCards_thenStatus200andValidUsersNumberReturn() throws Exception {
        //given
        Card firstCard = createNewCard();
        Card secondCard = createSecondCard();
        List<Card> list = List.of(firstCard, secondCard);

        //when
        when(cardService.findAllCards(any())).thenReturn(new PageImpl<>(list));

        //then
        mockMvc.perform(get("/v1/cards/pageable?number=1&size=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(list.size())));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "read_card")
    void findAllCardsByUserId_whenGetCards_thenStatus200andValidCardsNumberReturn() throws Exception {
        //given
        Card firstCard = createNewCard();
        Card secondCard = createSecondCard();
        List<Card> list = List.of(firstCard, secondCard);
        Long userId = 1L;

        //when
        when(cardService.getCardsByUserId(any())).thenReturn(list);

        //then
        mockMvc.perform(get("/v1/cards/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(list.size())));
    }
}