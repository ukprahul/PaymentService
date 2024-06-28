package com.ecommerce.paymentservice28jun24.service;

import com.razorpay.RazorpayException;

public interface PaymentService {
    String createPaymentLink(String orderId) throws RazorpayException;

    String getPaymentStatus(String paymentId);
    //String getPaymentStatus(String paymentId);
}
