package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("v1")
@RequiredArgsConstructor
@RestController("/cards")
@Api("Card controller")
public class CardController {
    private final CardService cardService;

    @GetMapping("/{cardId}")
    @ApiOperation("get card")
    public CardResponseDTO getCard(@PathVariable Long cardId) {
        return new CardResponseDTO(cardService.getCardById(cardId));
    }

    @PostMapping("/")
    @ApiOperation("create card")
    public Long createCard(@RequestBody CardRequestDTO cardRequestDTO) {
        return cardService.createCard(cardRequestDTO);
    }

    @PutMapping("/{cardId}")
    @ApiOperation("update card")
    public CardResponseDTO updateCard(@PathVariable Long cardId,
                                      @RequestBody CardRequestDTO cardRequestDTO) {
        return new CardResponseDTO(cardService.updateCard(cardId, cardRequestDTO));
    }

    @DeleteMapping("/{cardId}")
    @ApiOperation("delete card")
    public CardResponseDTO deleteCard(@PathVariable Long cardId) {
        return new CardResponseDTO(cardService.deleteCard(cardId));
    }

    @GetMapping("/user/{userId}")
    @ApiOperation("get all cards of some user")
    public List<CardResponseDTO> getCardsByUserId(@PathVariable Long userId) {
        return cardService.getCardsByUserId(userId).stream()
                .map(CardResponseDTO::new)
                .collect(Collectors.toList());
    }
}