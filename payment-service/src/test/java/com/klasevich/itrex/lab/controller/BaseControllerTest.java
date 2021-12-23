package com.klasevich.itrex.lab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.mapper.CardRequestDTOToCardMapper;
import com.klasevich.itrex.lab.service.CardService;
import com.klasevich.itrex.lab.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public abstract class BaseControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    public CardService cardService;

    @MockBean
    public CardRequestDTOToCardMapper cardRequestDTOToCardMapper;

    @MockBean
    public TransactionService transactionService;
}
