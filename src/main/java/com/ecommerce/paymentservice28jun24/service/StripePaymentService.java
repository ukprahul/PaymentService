package com.ecommerce.paymentservice28jun24.service;

import org.springframework.stereotype.Service;

@Service
public class StripePaymentService implements PaymentService{
    @Override
    public String createPaymentLink(String orderId) {
        return null;
    }

    @Override
    public String getPaymentStatus(String paymentId) {
        return null;
    }
}
