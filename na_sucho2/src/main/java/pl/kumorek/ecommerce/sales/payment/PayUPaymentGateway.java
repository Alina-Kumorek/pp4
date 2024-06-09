package pl.kumorek.ecommerce.sales.payment;

import main.java.pl.kumorek.ecommerce.sales.payu.OrderCreateRequest;

public class PayUPaymentGateway implements PaymentGateway{



    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        var request = new OrderCreateRequest();

        request.setNotifyUrl(""); //itd

        OrderCreateResponse response = payU.handle(request);

        return new PaymentDetails(response.getOrderId(), resoponse.redirectUrl);
    }

}
