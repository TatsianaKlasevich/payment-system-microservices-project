package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.PaymentRequestDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/deposit")
    @ApiOperation("make deposit")
    public PaymentResponseDTO deposit(@RequestBody PaymentRequestDTO requestDTO) {
        return paymentService.deposit(requestDTO.getUserId(), requestDTO.getCardId(), requestDTO.getAmount());
    }
}
