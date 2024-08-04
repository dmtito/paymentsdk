package com.mycompany.paymentsdk.processor;

import com.mycompany.paymentsdk.domain.PaymentMethod;
import com.mycompany.paymentsdk.exception.PaymentException;

public class PaymentProcessorFactory {
    public static PaymentProcessor getPaymentProcessor(PaymentMethod type) throws PaymentException {
        switch (type) {
            case CREDIT_CARD:
                return new CreditCardProcessor();
            case PAYPAL:
                return new PayPalProcessor();
            case CRYPTO:
                return new CryptoProcessor();
            default:
                throw new PaymentException("Unsupported payment processor type: " + type);
        }
    }
}
