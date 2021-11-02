package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController("/cards")
public class CardController {
    private final CardService cardService;

    @GetMapping("/{cardId}")
    public CardResponseDTO getCard(@PathVariable Long cardId) {
        return new CardResponseDTO(cardService.getCardById(cardId));
    }

    @PostMapping("/")
    public Long createCard(@RequestBody CardRequestDTO cardRequestDTO) {
        return cardService.createCard(cardRequestDTO);
    }

    @PutMapping("/{cardId}")
    public CardResponseDTO updateCard(@PathVariable Long cardId,
                                      @RequestBody CardRequestDTO cardRequestDTO) {
        return new CardResponseDTO(cardService.updateCard(cardId, cardRequestDTO));
    }

    @DeleteMapping("/{cardId}")
    public CardResponseDTO deleteCard(@PathVariable Long cardId) {
        return new CardResponseDTO(cardService.deleteCard(cardId));
    }

    @GetMapping("/user/{userId}")
    public List<CardResponseDTO> getCardsByUserId(@PathVariable Long userId) {
        return cardService.getCardsByUserId(userId).stream()
                .map(CardResponseDTO::new)
                .collect(Collectors.toList());
    }
}
