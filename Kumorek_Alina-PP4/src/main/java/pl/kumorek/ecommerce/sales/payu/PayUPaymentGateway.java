package pl.kumorek.ecommerce.sales.payu;

import org.springframework.web.client.RestTemplate;
import pl.kumorek.ecommerce.sales.payment.PaymentDetails;
import pl.kumorek.ecommerce.sales.payment.PaymentGateway;
import pl.kumorek.ecommerce.sales.payment.RegisterPaymentRequest;

public class PayUPaymentGateway implements PaymentGateway {
    PayU payU;

    public PayUPaymentGateway(PayU payU) {
        this.payU = payU;
    }

    public static PayUPaymentGateway sandbox() {
        return new PayUPaymentGateway(
                new PayU(
                        new RestTemplate(),
                        PayUCredentials.sandbox(
                                "300746",
                                "2ee86a66e5d97e3fadc400c9f19b065d"
                        )));
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        var request = new OrderCreateRequest();

        request
                .setNotifyUrl("https://your.eshop.com/notify")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("A Test Shop")
                .setCurrencyCode("PLN")
                .setTotalAmount(registerPaymentRequest.getTotalAsGrosze())
                .setExtOrderId(registerPaymentRequest.getReservationId())
                .setBuyer(
                        new Buyer()
                                .setEmail(registerPaymentRequest.getEmail())
                                .setPhone(registerPaymentRequest.getPhone())
                                .setFirstName(registerPaymentRequest.getFirstName())
                                .setLastName(registerPaymentRequest.getLastName())
                                .setLanguage("pl")
                )
                .setProducts(registerPaymentRequest.getProducts());

        OrderCreateResponse response = payU.handle(request);

        return new PaymentDetails(response.getOrderId(), response.getRedirectUri());
    }
}
