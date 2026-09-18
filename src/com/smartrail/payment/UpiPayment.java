package com.smartrail.payment;

public class UpiPayment implements PaymentMethod {
    private String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI payment of ₹" + amount + " for ID " + upiId + "...");
        // Simulated payment logic
        return true; // assume success
    }
}
