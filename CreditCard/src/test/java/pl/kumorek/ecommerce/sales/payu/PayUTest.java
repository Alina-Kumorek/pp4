package pl.kumorek.ecommerce.sales.payu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PayUTest {

    @Test
    void itRegisterNewPayment() {
        PayU payu = thereIsPayU();
        OrderCreateRequest request = thereIsExampleOrderCreateRequest();

        OrderCreateResponse response = payu.handle(request);

        assertNotNull(response.getOrderId());
        assertNotNull(response.redirectUrl());
    }

    private PayU thereIsPayU() {
        return new PayU(new RestTemplate(), PayUCredentials.sandbox());
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {
        var request = new OrderCreateRequest();

        request.setNotifyUrl("https://your.eshop.com/notify"); //itd
    }
}
