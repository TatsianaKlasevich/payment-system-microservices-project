package com.klasevich.itrex.lab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.mapper.*;
import com.klasevich.itrex.lab.service.CardService;
import com.klasevich.itrex.lab.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@WebMvcTest
public abstract class BaseControllerTest {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    public CardService cardService;

    @MockBean
    public TransactionService transactionService;

    @MockBean
    public CardRequestDTOToCardMapper cardRequestDTOToCardMapper;

    @MockBean
    public CardToCardResponseDTOMapper cardToCardResponseDTOMapper;

    @MockBean
    public DepositRequestDTOToTransactionMapper depositRequestDTOToTransactionMapper;

    @MockBean
    public PaymentRequestDTOToTransactionMapper paymentRequestDTOToTransactionMapper;

    @MockBean
    public TransferRequestDTOToTransactionMapper transferRequestDTOToTransactionMapper;

    @MockBean
    public TransactionToTransactionResponseDTOMapper transactionToTransactionResponseDTOMapper;
}
