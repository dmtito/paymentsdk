package com.mycompany;

import com.mycompany.paymentsdk.domain.Currency;
import com.mycompany.paymentsdk.domain.PaymentRequest;
import com.mycompany.paymentsdk.domain.PaymentResponse;
import com.mycompany.paymentsdk.domain.ResponseStatus;
import com.mycompany.paymentsdk.exception.PaymentException;
import com.mycompany.paymentsdk.processor.PayPalProcessor;
import com.mycompany.paymentsdk.processor.PaymentProcessor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.mycompany.paymentsdk.domain.PaymentMethod.PAYPAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PayPalProcessorTest {

    @ParameterizedTest
    @EnumSource(Currency.class)
    public void testProcessPayment(Currency currency) {
        PaymentProcessor processor = new PayPalProcessor();
        PaymentRequest request = new PaymentRequest(PAYPAL, "paypal_account", 50.00, currency);
        try {
            PaymentResponse response = processor.processPayment(request);
            assertNotNull(response.transactionId());
            assertEquals(ResponseStatus.SUCCESS, response.status());
        } catch (PaymentException e) {
            assertEquals("PayPal server not responding", e.getMessage());
        }
    }
}
