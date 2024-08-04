package com.mycompany.paymentsdk.domain;

public record PaymentRequest(
        PaymentMethod paymentMethod,
        String accountId,
        double amount,
        Currency currency) {
}
