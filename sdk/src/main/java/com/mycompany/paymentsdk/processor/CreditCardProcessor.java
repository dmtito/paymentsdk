package com.mycompany.paymentsdk.processor;

import com.mycompany.paymentsdk.domain.PaymentRequest;
import com.mycompany.paymentsdk.domain.PaymentResponse;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mycompany.paymentsdk.domain.ResponseStatus.FAIL;
import static com.mycompany.paymentsdk.domain.ResponseStatus.SUCCESS;

public class CreditCardProcessor implements PaymentProcessor {
    private static final Logger logger = Logger.getLogger(CreditCardProcessor.class.getName());

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        String transactionId = UUID.randomUUID().toString();
        logger.info("CreditCardProcessor: Start processing request with transactionId: " + transactionId);
        return (ThreadLocalRandom.current().nextInt() % 2 == 0) ?
                new PaymentResponse(transactionId, SUCCESS) :
                new PaymentResponse(transactionId, FAIL);
    }
}
