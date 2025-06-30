package com.solona.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.solona.modal.Order;
import com.solona.modal.PaymentOrder;
import com.solona.modal.User;
import com.stripe.exception.StripeException;

import java.util.*;

public interface PaymentService {
    PaymentOrder createOrder(User user, Set<Order>orders);
    PaymentOrder getPaymentOrderById(Long orderId) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;
    Boolean ProcessedPaymentOrder(PaymentOrder paymentOrder,String paymentId, String paymentLinkId) throws RazorpayException;
    PaymentLink createRazorpayPaymentLink(User user, Long amount, Long orderId) throws RazorpayException;
    String createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException;
}
