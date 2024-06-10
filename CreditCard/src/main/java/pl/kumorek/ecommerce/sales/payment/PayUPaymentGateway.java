package pl.kumorek.ecommerce.sales.payment;

public class PayUPaymentGateway implements PaymentGateway {
    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        var request = new OrderCreateRequest();

        request.setNotifyUrl(""); //itd

        OrderCreateResponse response = payU.handle(request);

        return new PaymentDetails(response.getOrderId(), resoponse.redirectUrl);
    }
}
