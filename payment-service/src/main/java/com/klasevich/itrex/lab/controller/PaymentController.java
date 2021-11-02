package com.klasevich.itrex.lab.controller;

import com.klasevich.itrex.lab.controller.dto.PaymentRequestDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/deposit")
    public PaymentResponseDTO deposit(@RequestBody PaymentRequestDTO requestDTO) {
        return paymentService.deposit(requestDTO.getUserId(), requestDTO.getCardId(), requestDTO.getAmount());
    }
}
