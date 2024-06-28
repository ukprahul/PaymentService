package com.ecommerce.paymentservice28jun24.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RazorpayPaymentService implements PaymentService{

    private RazorpayClient razorpayClient;
    @Autowired
    public RazorpayPaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(String orderId) throws RazorpayException {
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",1000);
            paymentLinkRequest.put("currency","INR");
            paymentLinkRequest.put("accept_partial",false);
            //paymentLinkRequest.put("first_min_partial_amount",100);
            paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 15 * 60 * 1000); // this is epoch time so get the method for current epoch time and add the required for expiry like 15 mins
            paymentLinkRequest.put("reference_id",orderId);
            paymentLinkRequest.put("description","Payment for order no "+ orderId);

            JSONObject customer = new JSONObject();
            customer.put("name","Rahul K P");
            customer.put("contact","8208238743");
            customer.put("email","rahulkp@gmail.com");
            paymentLinkRequest.put("customer",customer);

            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);

            paymentLinkRequest.put("reminder_enable",true);
            //JSONObject notes = new JSONObject();
            //notes.put("policy_name","Jeevan Bima");
            //paymentLinkRequest.put("notes",notes);
            paymentLinkRequest.put("callback_url","https://google.co.in/");
            paymentLinkRequest.put("callback_method","get");
            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
            return payment.get("short_url");
        }
        catch (Exception e){
            e.printStackTrace();
            return "null";
        }
    }

    @Override
    public String getPaymentStatus(String paymentId) {
        return null;
    }
}

//            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
//            return payment.toString();
//            JSONObject paymentLinkRequest = new JSONObject();
//            paymentLinkRequest.put("amount",1000);
//            paymentLinkRequest.put("currency","INR");
//            paymentLinkRequest.put("accept_partial",false);
//            //paymentLinkRequest.put("first_min_partial_amount",100);
//            paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 15 * 60 * 1000);
//            paymentLinkRequest.put("reference_id",orderId);
//            paymentLinkRequest.put("description","Payment for policy no"+orderId);
//            JSONObject customer = new JSONObject();
//            customer.put("name","Rahul K P");
//            customer.put("contact","8220962249");
//            customer.put("email","rahul@example.com");
//            paymentLinkRequest.put("customer",customer);
//            JSONObject notify = new JSONObject();
//            notify.put("sms",true);
//            notify.put("email",true);
//            paymentLinkRequest.put("reminder_enable",true);
//            JSONObject notes = new JSONObject();
//            notes.put("policy_name","Jeevan Bima");
//            paymentLinkRequest.put("notes",notes);
//            paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
//            paymentLinkRequest.put("callback_method","get");