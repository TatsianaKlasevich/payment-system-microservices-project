package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/cards")
@RestController
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{cardId}")
    public CardResponseDTO getCard(@PathVariable int cardId) {
        return new CardResponseDTO(cardService.getCardById(cardId));
    }

    @PostMapping("/")
    public Integer createCard(@RequestBody CardRequestDTO cardRequestDTO) {
        return cardService.createCard(cardRequestDTO.getBalance(),
                cardRequestDTO.getCurrency(), cardRequestDTO.getCardNumber(), cardRequestDTO.isDefault(), cardRequestDTO.getCardStatus(),
                cardRequestDTO.getExpirationDate(), cardRequestDTO.getCvvCode(), cardRequestDTO.getPersonId());
    }

    @PutMapping("/{cardId}")
    public CardResponseDTO updateCard(@PathVariable Integer cardId,
                                      @RequestBody CardRequestDTO cardRequestDTO) {
        return new CardResponseDTO(cardService.updateCard(cardId, cardRequestDTO.getBalance(),
                cardRequestDTO.getCurrency(), cardRequestDTO.getCardNumber(), cardRequestDTO.isDefault(), cardRequestDTO.getCardStatus(),
                cardRequestDTO.getExpirationDate(), cardRequestDTO.getCvvCode(), cardRequestDTO.getPersonId()));
    }

    @DeleteMapping("/{cardId}")
    public CardResponseDTO deleteCard(@PathVariable Integer cardId) {
        return new CardResponseDTO(cardService.deleteCard(cardId));
    }

    @GetMapping("/user/{userId}")
    public List<CardResponseDTO> getCardsByPersonId(@PathVariable int userId) {
        return cardService.getCardsByUserId(userId).stream()
                .map(CardResponseDTO::new)
                .collect(Collectors.toList());
    }
}
