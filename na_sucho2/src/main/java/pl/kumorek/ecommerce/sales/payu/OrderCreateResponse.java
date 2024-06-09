package main.java.pl.kumorek.ecommerce.sales.payu;

//@Data
public class OrderCreateResponse {
    Status status;
    String redirectUrl;
    String orderId;
    String extOrderId;

    public String getOrderId() {
        return orderId;
    }

    public String redirectUrl() {
        return redirectUrl;
    }
}
