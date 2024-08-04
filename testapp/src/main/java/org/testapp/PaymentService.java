package org.testapp;

import com.mycompany.paymentsdk.domain.PaymentMethod;
import com.mycompany.paymentsdk.domain.PaymentRequest;
import com.mycompany.paymentsdk.exception.PaymentException;
import com.mycompany.paymentsdk.processor.PaymentProcessor;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import static com.mycompany.paymentsdk.domain.Currency.*;
import static com.mycompany.paymentsdk.domain.PaymentMethod.*;
import static com.mycompany.paymentsdk.processor.PaymentProcessorFactory.getPaymentProcessor;
import static java.util.logging.Logger.getLogger;

public class PaymentService {
    private static final Logger logger = getLogger(PaymentService.class.getName());

    private final PaymentProcessor paymentProcessor;

    public PaymentService(PaymentMethod paymentMethod) {
        this.paymentProcessor = getPaymentProcessor(paymentMethod);
    }

    public static void main(String[] args) {
        var serviceMap = new ConcurrentHashMap<PaymentMethod, PaymentService>();
        var requestList = List.of(
                new PaymentRequest(PAYPAL, "1234444444", 100.00, USD),
                new PaymentRequest(PAYPAL, "1234567890", 56.00, USD),
                new PaymentRequest(PAYPAL, "1234567890", 56.00, USD),
                new PaymentRequest(CREDIT_CARD, "1234 5600 9990 9790", 100.00, UAH),
                new PaymentRequest(CREDIT_CARD, "1234 5600 9090 9496", 42.00, UAH),
                new PaymentRequest(CRYPTO, "123456asdf123", 1.00, BTC),
                new PaymentRequest(CRYPTO, "535536asdf123", 0.90, BTC),
                new PaymentRequest(PAYPAL, "1234567890", 56.00, USD)
        );

        requestList.forEach(request -> {
            serviceMap.computeIfAbsent(request.paymentMethod(), PaymentService::new).makePayment(request);
        });
    }

    public void makePayment(PaymentRequest request) {
        try {
            var response = paymentProcessor.processPayment(request);
            logger.info(String.format("Payment Response STATUS for payment method %s transactionId %s: %s%n",
                    request.paymentMethod(), response.transactionId(), response.status()));
        } catch (PaymentException e) {
            logger.severe(String.format("Error on handling request for account %s: %s%n",
                    request.accountId(), e.getMessage()));
        }
    }
}
