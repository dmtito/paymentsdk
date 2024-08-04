package com.mycompany;

import com.mycompany.paymentsdk.domain.Currency;
import com.mycompany.paymentsdk.domain.PaymentRequest;
import com.mycompany.paymentsdk.domain.PaymentResponse;
import com.mycompany.paymentsdk.domain.ResponseStatus;
import com.mycompany.paymentsdk.processor.CryptoProcessor;
import com.mycompany.paymentsdk.processor.PaymentProcessor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.mycompany.paymentsdk.domain.PaymentMethod.CRYPTO;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CryptoProcessorTest {
    @ParameterizedTest
    @EnumSource(Currency.class)
    public void testProcessPayment(Currency currency) {
        PaymentProcessor processor = new CryptoProcessor();
        PaymentRequest request = new PaymentRequest(CRYPTO, "crypto_account", 1.00, currency);
        PaymentResponse response = processor.processPayment(request);

        assertNotNull(response.transactionId());
        assertTrue(response.status() == ResponseStatus.SUCCESS || response.status() == ResponseStatus.FAIL);
    }
}
