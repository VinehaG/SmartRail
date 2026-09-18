package com.smartrail.payment;

public class CardPayment implements PaymentMethod {
    private String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Card payment of ₹" + amount + " for card ending in " + cardNumber.substring(Math.max(0, cardNumber.length() - 4)) + "...");
        // Simulated payment logic
        return true; // assume success
    }
}
