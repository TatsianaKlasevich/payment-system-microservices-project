package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.mapper.CardRequestDTOToCardMapper;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("v1")
@RequiredArgsConstructor
@Api("Card controller")
@RestController("/")
public class CardController {
    private final CardService cardService;
    private final CardRequestDTOToCardMapper cardRequestDTOToCardMapper;

    @GetMapping("cards/{cardId}")
    @ApiOperation("Get card by id")
    @PreAuthorize("hasAuthority('read_card')")
    public CardResponseDTO getCard(@PathVariable Long cardId) {
        return new CardResponseDTO(cardService.getCardById(cardId));
    }

    @PostMapping("cards/")
    @ApiOperation("Create card")
    @PreAuthorize("hasAuthority('create_card')")
    public Long createCard(@RequestBody @Valid CardRequestDTO cardRequestDTO) {
        Card card = cardRequestDTOToCardMapper.convert(cardRequestDTO);
        return cardService.createCard(card);
    }

    @PutMapping("cards/{cardId}")
    @ApiOperation("Update card")
    @PreAuthorize("hasAuthority('update_card')")
    public CardResponseDTO updateCard(@PathVariable Long cardId,
                                      @RequestBody @Valid CardRequestDTO cardRequestDTO) {
        Card card = cardRequestDTOToCardMapper.convert(cardRequestDTO);
        card.setUserId(cardId);
        return new CardResponseDTO(cardService.updateCard(card));
    }

    @DeleteMapping("cards/{cardId}")
    @ApiOperation("Delete card")
    @PreAuthorize("hasAuthority('delete')")
    public CardResponseDTO deleteCard(@PathVariable Long cardId) {
        return new CardResponseDTO(cardService.deleteCard(cardId));
    }

    @GetMapping("cards/user/{userId}")
    @ApiOperation("Get all cards of some user")
    @PreAuthorize("hasAuthority('read_card')")
    public List<CardResponseDTO> getCardsByUserId(@PathVariable Long userId) {
        return cardService.getCardsByUserId(userId).stream()
                .map(CardResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("cards/page")
    @ApiOperation("Get all cards by some page and sort")
    @PreAuthorize("hasAuthority('read_all')")
    public List<CardResponseDTO> findAllCards(Pageable pageable) {
        return cardService.findAllCards(pageable).stream()
                .map(CardResponseDTO::new)
                .collect(Collectors.toList());
    }
}
