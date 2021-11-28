package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.*;
import com.klasevich.itrex.lab.mapper.DepositRequestDTOToTransactionMapper;
import com.klasevich.itrex.lab.mapper.PaymentRequestDTOToTransactionMapper;
import com.klasevich.itrex.lab.mapper.TransferRequestDTOToTransactionMapper;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import com.klasevich.itrex.lab.persistance.entity.TransactionType;
import com.klasevich.itrex.lab.service.CardService;
import com.klasevich.itrex.lab.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("v1")
@RequiredArgsConstructor
@RestController
@Api("Payment controller")
public class TransactionController {
    private final TransactionService transactionService;
    private final CardService cardService;
    private final DepositRequestDTOToTransactionMapper depositRequestDTOToTransactionMapper;
    private final PaymentRequestDTOToTransactionMapper paymentRequestDTOToTransactionMapper;
    private final TransferRequestDTOToTransactionMapper transferRequestDTOToTransactionMapper;

    @GetMapping("/{cardId}")
    @ApiOperation("Get all payment transactions by some card id")
    @PreAuthorize("hasAnyAuthority('read_card', 'create_card', 'read_user')")
    public List<TransactionResponseDTO> getAllTransactionsByCardId(@PathVariable Long cardId) {
        return transactionService.getTransactionsByCardId(cardId).stream()
                .map(TransactionResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/page")
    @ApiOperation("Show all payments by some page and sort")
    @PreAuthorize("hasAnyAuthority('read_card', 'create_card', 'read_user')")
    public List<TransactionResponseDTO> getAllTransactions(Pageable pageable) {
        return transactionService.getAllTransactions(pageable).stream()
                .map(TransactionResponseDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/deposit")
    @ApiOperation("Make deposit")
    @PreAuthorize("hasAnyAuthority('read_card', 'create_card', 'read_user')")
    public DepositResponseDTO createDeposit(@RequestBody @Valid DepositRequestDTO depositRequestDTO) {
        Transaction transaction = depositRequestDTOToTransactionMapper.convert(depositRequestDTO);
        Card card = cardService.getCardById(depositRequestDTO.getCardId());
        transaction.setCard(card);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        return transactionService.createDeposit(transaction);
    }

    @PostMapping("/payment")
    @ApiOperation("Make payment")
    @PreAuthorize("hasAnyAuthority('read_card', 'create_card', 'read_user')")
    public PaymentResponseDTO createPayment(@RequestBody @Valid PaymentRequestDTO paymentRequestDTO) {
        Transaction transaction = paymentRequestDTOToTransactionMapper.convert(paymentRequestDTO);
        Card card = cardService.getCardById(paymentRequestDTO.getCardId());
        transaction.setCard(card);
        transaction.setTransactionType(TransactionType.PAYMENT);
        return transactionService.createPayment(transaction);
    }

    @PostMapping("/transfer")
    @ApiOperation("Make transfer")
    @PreAuthorize("hasAnyAuthority('read_card', 'create_card', 'read_user')")
    public TransferResponseDTO createTransfer(@RequestBody @Valid TransferRequestDTO transferRequestDTO) {
        Transaction transaction = transferRequestDTOToTransactionMapper.convert(transferRequestDTO);
        Card card = cardService.getCardById(transferRequestDTO.getCardId());
        transaction.setCard(card);
        transaction.setTransactionType(TransactionType.TRANSFER);
        return transactionService.createTransfer(transaction);
    }
}
