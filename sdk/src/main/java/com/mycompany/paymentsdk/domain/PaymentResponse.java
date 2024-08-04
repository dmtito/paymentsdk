package com.mycompany.paymentsdk.domain;

public record PaymentResponse(
        String transactionId,
        ResponseStatus status) {
}
