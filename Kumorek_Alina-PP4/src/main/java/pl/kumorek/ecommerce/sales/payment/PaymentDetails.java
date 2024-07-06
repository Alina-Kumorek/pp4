package pl.kumorek.ecommerce.sales.payment;

public class PaymentDetails {
    String transactionId;
    String paymentUrl;

    public PaymentDetails(String transactionId, String paymentUrl) {
        this.transactionId = transactionId;
        this.paymentUrl = paymentUrl;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }
}
