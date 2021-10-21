package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.service.CardService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{id")
    public CardResponseDTO getCard(@PathVariable Integer id){
        return new CardResponseDTO(cardService.getCardById(id));
    }

    @PostMapping("/")
    public Integer createCard(@RequestBody CardRequestDTO cardRequestDTO){
       return cardService.createCard(cardRequestDTO.getBalance(),
               cardRequestDTO.getCurrency(), cardRequestDTO.getCardNumber(), cardRequestDTO.getCardStatus(),
               cardRequestDTO.getExpirationDate(), cardRequestDTO.getCvvCode(), cardRequestDTO.getUserId());
    }

    @PutMapping("/{id}")
    public CardResponseDTO updateCard(@PathVariable Integer id,
                                      @RequestBody CardRequestDTO cardRequestDTO){
        return new CardResponseDTO(cardService.updateCard(id, cardRequestDTO.getBalance(),
                cardRequestDTO.getCurrency(), cardRequestDTO.getCardNumber(), cardRequestDTO.getCardStatus(),
                cardRequestDTO.getExpirationDate(), cardRequestDTO.getCvvCode(), cardRequestDTO.getUserId()));
    }
    
    @DeleteMapping("/{id}")
    public CardResponseDTO deleteCard(@PathVariable Integer id){
        return new CardResponseDTO(cardService.deleteCard(id));
    }
}
