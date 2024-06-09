package test.java.pl.kumorek.ecommerce.payu;

import main.java.pl.kumorek.ecommerce.sales.payu.*;

public class PayUTest {

    @Test
    void itRegisterNewPayment() {
        PayU payu = thereIsPayU();
        OrderCreateRequest request = thereIsExampleOrderCreateRequest();

        OrderCreateResponse response = payu.handle(request);

        asertNotNull(response.getOrderId());
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
