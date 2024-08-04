package com.mycompany.paymentsdk.processor;

import com.mycompany.paymentsdk.domain.PaymentRequest;
import com.mycompany.paymentsdk.domain.PaymentResponse;
import com.mycompany.paymentsdk.exception.PaymentException;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

import static com.mycompany.paymentsdk.domain.ResponseStatus.SUCCESS;

public class PayPalProcessor implements PaymentProcessor {
    private static final Logger logger = Logger.getLogger(PayPalProcessor.class.getName());


    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        String transactionId = UUID.randomUUID().toString();
        logger.info("PayPalProcessor: Start processing request with transactionId: " + transactionId);
        if (ThreadLocalRandom.current().nextInt() % 2 == 0) {
            return new PaymentResponse(transactionId, SUCCESS);
        }
        String payPalServiceErrorMessage = "PayPal server not responding";
        logger.severe(payPalServiceErrorMessage + " TransactionId: " + transactionId);
        throw new PaymentException(payPalServiceErrorMessage);
    }
}
