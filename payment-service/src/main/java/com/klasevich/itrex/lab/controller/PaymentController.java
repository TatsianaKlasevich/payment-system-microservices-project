package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.DepositRequestDTO;
import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.mappers.DepositRequestDTOToPaymentMapper;
import com.klasevich.itrex.lab.persistance.entity.Payment;
import com.klasevich.itrex.lab.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("v1")
@RequiredArgsConstructor
@RestController
@Api("Payment controller")
public class PaymentController {
    private final PaymentService paymentService;
    private final DepositRequestDTOToPaymentMapper depositRequestDTOToPaymentMapper;

    @PostMapping("/deposit")
    @ApiOperation("Make deposit")
    @PreAuthorize("hasAnyAuthority('read_card', 'create_card', 'read_user')")
    public DepositResponseDTO deposit(@RequestBody DepositRequestDTO depositRequestDTO) {
        Payment payment = depositRequestDTOToPaymentMapper.convert(depositRequestDTO);
        return paymentService.deposit(payment);
    }
}
