package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.mappers.CardRequestDTOToCardMapper;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("v1")
@RequiredArgsConstructor
@RestController("/cards")
@Api("Card controller")
public class CardController {
    private final CardService cardService;
    private final CardRequestDTOToCardMapper cardRequestDTOToCardMapper;

    @GetMapping("/{cardId}")
    @ApiOperation("Get card by id")
    @PreAuthorize("hasAuthority('read_card')")
    public CardResponseDTO getCard(@PathVariable Long cardId) {
        return new CardResponseDTO(cardService.getCardById(cardId));
    }

    @PostMapping("/")
    @ApiOperation("Create card")
    @PreAuthorize("hasAuthority('create_card')")
    public Long createCard(@RequestBody CardRequestDTO cardRequestDTO) {
        Card card = cardRequestDTOToCardMapper.convert(cardRequestDTO);
        return cardService.createCard(card);
    }

    @PutMapping("/{cardId}")
    @ApiOperation("Update card")
    @PreAuthorize("hasAuthority('update_card')")
    public CardResponseDTO updateCard(@PathVariable Long cardId,
                                      @RequestBody CardRequestDTO cardRequestDTO) {
        Card card = cardRequestDTOToCardMapper.convert(cardRequestDTO);
        card.setUserId(cardId);
        return new CardResponseDTO(cardService.updateCard(card));
    }

    @DeleteMapping("/{cardId}")
    @ApiOperation("Delete card")
    @PreAuthorize("hasAuthority('delete')")
    public CardResponseDTO deleteCard(@PathVariable Long cardId) {
        return new CardResponseDTO(cardService.deleteCard(cardId));
    }

    @GetMapping("/user/{userId}")
    @ApiOperation("Get all cards of some user")
    @PreAuthorize("hasAuthority('read_card')")
    public List<CardResponseDTO> getCardsByUserId(@PathVariable Long userId) {
        return cardService.getCardsByUserId(userId).stream()
                .map(CardResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/")
    @ApiOperation("Get all cards")
    @PreAuthorize("hasRole('BANK_EMPLOYEE')")
    public List<CardResponseDTO> findAllCards() {
        return cardService.findAllCards();
    }
}
