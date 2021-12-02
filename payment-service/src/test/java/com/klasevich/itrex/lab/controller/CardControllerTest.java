package com.klasevich.itrex.lab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.service.CardService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.klasevich.itrex.lab.util.TestData.createCardResponseDTO;
import static com.klasevich.itrex.lab.util.TestData.createNewCard;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CardService cardService;

    @Test
    void getCard() {
    }

    //todo check why security doesn't work
    private String obtainAccessToken(String username, String password) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "mobile");
        params.add("username", username);
        params.add("password", password);

        ResultActions result
                = mockMvc.perform(post("/oauth/token")
                        .params(params)
                        .with(httpBasic("mobile", "pin"))
                        .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    void createCard_whenValidCardData_createAndReturnTheCard() throws Exception {
        //give
        String accessToken = obtainAccessToken("gleb", "kpass");
        Card card = createNewCard();
        CardResponseDTO cardResponseDTO = createCardResponseDTO();

        //when
        when(cardService.getCardById(anyLong())).thenReturn(card);
        mockMvc.perform(post("/payments/cards/")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cardResponseDTO)))
                .andExpect(status().is2xxSuccessful());
    }
}