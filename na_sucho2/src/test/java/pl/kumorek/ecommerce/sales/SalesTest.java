package pl.kumorek.ecommerce.sales;

import pl.kumorek.ecommerce.sales.cart.CartStorage;
import pl.kumorek.ecommerce.sales.offer.AcceptOfferRequest;
import pl.kumorek.ecommerce.sales.offer.Offer;
import pl.kumorek.ecommerce.sales.reservation.ReservationDetails;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

public class SalesTest {
    @Test
    void itShowsCurrentOffer() {
        //Arrange
        SalesFacade sales = thereIsSalesModule();
        String customerId = thereIsCustomer("Kuba");

        //A
        Offer offer = sales.getCurrentOffer(customerId);

        //A
        assertEquals(BigDecimal.ZERO, offer.getTotal());
        assertEquals(0, offer.getIgtemsCount());
    }

    @Test
    void itAddProductToCart() {
        //Arrange
        SalesFacade sales = thereIsSalesModule();
        String customerId = thereIsCustomer("Kuba");
        String productId = thereIsProduct("example product", BigDecimal.valueOf(10));

        //A
        sales.addProduct(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        //A
        assertEquals(BigDecimal.valueOf(10), offer.getTotal());
        assertEquals(1, offer.getIgtemsCount());
    }

    @Test
    void itAcceptOffer() {
        //Arrange
        SalesFacade sales = thereIsSalesModule();
        String customerId = thereIsCustomer("Kuba");
        String productId = thereIsProduct("example product", BigDecimal.valueOf(10));

        //A
        sales.addProduct(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);
        AcceptOfferRequest request = new AcceptOfferRequest();

        request.setEmail("john.doe@example.com");
        request.setFirstName("John");
        request.setLastName("Doe");

        ReservationDetails reservationDetails = sales.acceptOffer(customerId, request);

        //A
        assertNotNull(reservationDetails.getReservationId());
        assertNotNull(reservationDetails.getPaymentUrl());
    }

    @Test
    void itAcceptPaymentForOrder() {

    }

    private String thereIsCustomer(String name) {
        return name;
    }

    private SalesFacade thereIsSalesModule() {
        return new SalesFacade(new CartStorage());
    }

    private String thereIsProduct(String productName, BigDecimal price) {
        return productName;
    }
}
