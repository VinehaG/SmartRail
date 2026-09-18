package com.smartrail.payment;

public interface PaymentMethod {
    boolean processPayment(double amount);
}
