package com.ecommerce.paymentservice28jun24.controller;


import com.ecommerce.paymentservice28jun24.dtos.CreatePaymentLinkRequestDto;
import com.ecommerce.paymentservice28jun24.service.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class paymentController {
    private PaymentService paymentService;

    public paymentController(PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String createPaymentLink(@RequestBody CreatePaymentLinkRequestDto request) throws RazorpayException {
        return paymentService.createPaymentLink(request.getOrderId());

    }
}
