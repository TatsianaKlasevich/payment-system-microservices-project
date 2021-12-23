package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.*;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionControllerTest extends BaseControllerTest {

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "read_all")
    void findAllTransactionsBySomePage_whenGetTransactions_thenStatus200andValidTransactionsNumberReturn() throws Exception {
        //given
        Card firstCard = createNewCard();
        Card secondCard = createSecondCard();
        Transaction firstTransaction = createDepositTransaction(firstCard);
        Transaction secondTransaction = createTransferTransaction(secondCard);
        List<Transaction> list = List.of(firstTransaction, secondTransaction);

        //when
        when(transactionService.getAllTransactions(any())).thenReturn(new PageImpl<>(list));

        //then
        mockMvc.perform(get("/v1/pageable?number=1&size=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(list.size())));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "read_card")
    void findAllTransactionsByCardId_whenGetTransactions_thenStatus200andValidTransactionsNumberReturn() throws Exception {
        //given
        Card firstCard = createNewCard();
        Card secondCard = createSecondCard();
        Transaction firstTransaction = createDepositTransaction(firstCard);
        Transaction secondTransaction = createTransferTransaction(secondCard);
        List<Transaction> list = List.of(firstTransaction, secondTransaction);
        Long cardId = 1L;

        //when
        when(transactionService.getTransactionsByCardId(any())).thenReturn(list);

        //then
        mockMvc.perform(get("/v1/{cardId}", cardId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(list.size())));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "make_transaction")
    void makeDeposit_whenGetDepositResponseDTO_thenStatus200andValidDataReturn() throws Exception {
        //given
        Card card = createNewCard();
        DepositResponseDTO depositResponseDTO = createDepositResponseDTO();
        DepositRequestDTO depositRequestDTO = createDepositRequestDTO();

        //when
        when(cardService.getCardById(anyLong())).thenReturn(card);
        when(transactionService.createDeposit(any())).thenReturn(depositResponseDTO);

        //then
        mockMvc.perform(post("/v1/deposit")
                        .content(objectMapper.writeValueAsString(depositRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mail").value(depositResponseDTO.getMail()));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "make_transaction")
    void makePayment_whenGetPaymentResponseDTO_thenStatus200andValidDataReturn() throws Exception {
        //given
        Card card = createNewCard();
        PaymentResponseDTO paymentResponseDTO = createPaymentResponseDTO();
        PaymentRequestDTO paymentRequestDTO = createPaymentRequestDTO();

        //when
        when(cardService.getCardById(anyLong())).thenReturn(card);
        when(transactionService.createPayment(any())).thenReturn(paymentResponseDTO);

        //then
        mockMvc.perform(post("/v1/payment")
                        .content(objectMapper.writeValueAsString(paymentRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mail").value(paymentResponseDTO.getMail()));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "make_transaction")
    void makeTransfer_whenGetTransferResponseDTO_thenStatus200andValidDataReturn() throws Exception {
        //given
        Card card = createNewCard();
        TransferResponseDTO transferResponseDTO = createTransferResponseDTO();
        TransferRequestDTO transferRequestDTO = createTransferRequestDTO();

        //when
        when(cardService.getCardById(anyLong())).thenReturn(card);
        when(transactionService.createTransfer(any())).thenReturn(transferResponseDTO);

        //then
        mockMvc.perform(post("/v1/transfer")
                        .content(objectMapper.writeValueAsString(transferRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mail").value(transferResponseDTO.getMail()));
    }
}