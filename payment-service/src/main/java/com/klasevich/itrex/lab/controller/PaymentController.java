package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.*;
import com.klasevich.itrex.lab.mapper.DepositRequestDTOToPaymentMapper;
import com.klasevich.itrex.lab.mapper.PaymentRequestDTOToPaymentMapper;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Payment;
import com.klasevich.itrex.lab.persistance.entity.PaymentType;
import com.klasevich.itrex.lab.service.CardService;
import com.klasevich.itrex.lab.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("v1")
@RequiredArgsConstructor
@RestController
@Api("Payment controller")
public class PaymentController {
    private final PaymentService paymentService;
    private final CardService cardService;
    private final DepositRequestDTOToPaymentMapper depositRequestDTOToPaymentMapper;
    private final PaymentRequestDTOToPaymentMapper paymentRequestDTOToPaymentMapper;

    @PostMapping("/deposit")
    @ApiOperation("Make deposit")
    @PreAuthorize("hasAnyAuthority('read_card', 'create_card', 'read_user')")
    public DepositResponseDTO createDeposit(@RequestBody DepositRequestDTO depositRequestDTO) {
        Payment payment = depositRequestDTOToPaymentMapper.convert(depositRequestDTO);
        Card card = cardService.getCardById(depositRequestDTO.getCardId());
        payment.setCard(card);
        payment.setPaymentType(PaymentType.DEPOSIT);
        return paymentService.deposit(payment);
    }

    @GetMapping("/{cardId}")
    @ApiOperation("Get all payment transactions by some card id")
    @PreAuthorize("hasAnyAuthority('read_card', 'create_card', 'read_user')")
    public List<TransactionResponseDTO> getAllPaymentsByCardId(@PathVariable Long cardId) {
        return paymentService.getPaymentsByCardId(cardId).stream()
                .map(TransactionResponseDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/payment")
    @ApiOperation("Get all payment transactions by some card id")
    @PreAuthorize("hasAnyAuthority('read_card', 'create_card', 'read_user')")
    public PaymentResponseDTO createPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        Payment payment = paymentRequestDTOToPaymentMapper.convert(paymentRequestDTO);
        Card card = cardService.getCardById(paymentRequestDTO.getCardId());
        payment.setCard(card);
        payment.setPaymentType(PaymentType.PAYMENT);
        return paymentService.createPayment(payment);
    }
}
