package pl.kumorek.ecommerce.sales.payu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;

public class PayUTest {

    @Test
    void itRegistersNewPayment() {
        PayU payu = thereIsPayU();
        OrderCreateRequest request = thereIsExampleOrderCreateRequest();

        OrderCreateResponse response = payu.handle(request);

        assertNotNull(response.getOrderId());
        assertNotNull(response.getRedirectUri());
    }

    private PayU thereIsPayU() {
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                )
        );
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {
        var request = new OrderCreateRequest();

        request
                .setNotifyUrl("https://your.eshop.com/notify")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("My digital product")
                .setCurrencyCode("PLN")
                .setTotalAmount(20000)
                .setExtOrderId(UUID.randomUUID().toString())
                .setBuyer(new Buyer()
                                .setEmail("john.doe@example.com")
                                .setPhone("123456789")
                                .setFirstName("John")
                                .setLastName("Doe")
                                .setLanguage("pl")
                        )
                .setProducts(
                        Arrays.asList(
                                new Product()
                                        .setName("A product")
                                        .setUnitPrice(20000)
                                        .setQuantity(1)
                                )
                        );

        return request;
    }
}
