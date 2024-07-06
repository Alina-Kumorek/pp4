package pl.kumorek.ecommerce.sales.reservation;

import pl.kumorek.ecommerce.sales.offer.AcceptOfferRequest;
import pl.kumorek.ecommerce.sales.offer.Offer;
import pl.kumorek.ecommerce.sales.payment.PaymentDetails;

import java.math.BigDecimal;

public class Reservation {
    private final String reservationId;
    private final BigDecimal total;
    private final CustomerData customerData;
    private final String transactionId;

    public Reservation(String reservationId, BigDecimal total, CustomerData customerData, String transactionId) {
        this.reservationId = reservationId;
        this.total = total;
        this.customerData = customerData;
        this.transactionId = transactionId;
    }

    public static Reservation of(String reservationId, Offer offer, AcceptOfferRequest request, PaymentDetails paymentDetails) {
        return new Reservation(
                reservationId,
                offer.getTotal(),
                new CustomerData(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhone()),
                paymentDetails.getTransactionId()
        );
    }

    public String getReservationId() {
        return reservationId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
