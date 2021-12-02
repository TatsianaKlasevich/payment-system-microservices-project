package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.DepositRequestDTO;
import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentRequestDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.controller.dto.TransactionResponseDTO;
import com.klasevich.itrex.lab.controller.dto.TransferRequestDTO;
import com.klasevich.itrex.lab.controller.dto.TransferResponseDTO;
import com.klasevich.itrex.lab.mapper.DepositRequestDTOToTransactionMapper;
import com.klasevich.itrex.lab.mapper.PaymentRequestDTOToTransactionMapper;
import com.klasevich.itrex.lab.mapper.TransactionToTransactionResponseDTOMapper;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final TransactionToTransactionResponseDTOMapper transactionToTransactionResponseDTOMapper;

    @GetMapping("/{cardId}")
    @ApiOperation("Get all payment transactions by some card id")
    @PreAuthorize("hasAuthority('read_card')")
    public List<TransactionResponseDTO> getAllTransactionsByCardId(@PathVariable Long cardId) {
        return transactionService.getTransactionsByCardId(cardId).stream()
                .map(transactionToTransactionResponseDTOMapper::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/page")
    @ApiOperation("Show all payments by some page and sort")
    @PreAuthorize("hasAuthority('read_all')")
    public List<TransactionResponseDTO> getAllTransactions(Pageable pageable) {
        return transactionService.getAllTransactions(pageable).stream()
                .map(transactionToTransactionResponseDTOMapper::convert)
                .collect(Collectors.toList());
    }

    @PostMapping("/deposit")
    @ApiOperation("Make deposit")
    @PreAuthorize("hasAuthority('make_transaction')")
    public DepositResponseDTO createDeposit(@RequestBody @Valid DepositRequestDTO depositRequestDTO) {
        Transaction transaction = depositRequestDTOToTransactionMapper.convert(depositRequestDTO);
        Card card = cardService.getCardById(depositRequestDTO.getCardId());
        transaction.setCard(card);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        return transactionService.createDeposit(transaction);
    }

    @PostMapping("/payment")
    @ApiOperation("Make payment")
    @PreAuthorize("hasAuthority('make_transaction')")
    public PaymentResponseDTO createPayment(@RequestBody @Valid PaymentRequestDTO paymentRequestDTO) {
        Transaction transaction = paymentRequestDTOToTransactionMapper.convert(paymentRequestDTO);
        Card card = cardService.getCardById(paymentRequestDTO.getCardId());
        transaction.setCard(card);
        transaction.setTransactionType(TransactionType.PAYMENT);
        return transactionService.createPayment(transaction);
    }

    @PostMapping("/transfer")
    @ApiOperation("Make transfer")
    @PreAuthorize("hasAuthority('make_transaction')")
    public TransferResponseDTO createTransfer(@RequestBody @Valid TransferRequestDTO transferRequestDTO) {
        Transaction transaction = transferRequestDTOToTransactionMapper.convert(transferRequestDTO);
        Card card = cardService.getCardById(transferRequestDTO.getCardId());
        transaction.setCard(card);
        transaction.setTransactionType(TransactionType.TRANSFER);
        return transactionService.createTransfer(transaction);
    }
}
