package com.klasevich.itrex.lab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.PaymentApplication;
import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.feign.UserServiceClient;
import com.klasevich.itrex.lab.feign.dto.UserResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import com.klasevich.itrex.lab.persistance.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.klasevich.itrex.lab.util.TestData.createUserResponseDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = {PaymentApplication.class})
@AutoConfigureMockMvc(addFilters = false)
public class TransactionControllerITTest {

    private static final String REQUEST = "{\n" +
            "    \"cardId\": 1,\n" +
            "    \"amount\": 300\n" +
            "}";
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TransactionRepository transactionRepository;
    @MockBean
    private UserServiceClient userServiceClient;
    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "make_transaction")
    public void makeDeposit_whenGetDepositResponseDTO_thenStatus200andValidDataReturn() throws Exception {
        //given
        UserResponseDTO userResponseDTO = createUserResponseDTO();

        //when
        when(userServiceClient.getUserById(anyLong())).thenReturn(userResponseDTO);

        //then
        MvcResult mvcResult = mockMvc.perform(post("/v1/deposit")
                        .content(REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        Transaction transactionByEmail = transactionRepository.findTransactionByEmail(userResponseDTO.getEmail());
        DepositResponseDTO depositResponseDTO = objectMapper.readValue(body, DepositResponseDTO.class);

        assertThat(depositResponseDTO.getMail()).isEqualTo(transactionByEmail.getEmail());
    }
}
