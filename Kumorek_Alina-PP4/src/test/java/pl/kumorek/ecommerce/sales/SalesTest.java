package pl.kumorek.ecommerce.sales;

import pl.kumorek.ecommerce.catalog.HashMapProductStorage;
import pl.kumorek.ecommerce.catalog.ProductCatalog;
import pl.kumorek.ecommerce.sales.cart.CartStorage;
import pl.kumorek.ecommerce.sales.offer.AcceptOfferRequest;
import pl.kumorek.ecommerce.sales.offer.Offer;
import pl.kumorek.ecommerce.sales.offer.OfferCalculator;
import pl.kumorek.ecommerce.sales.payment.PaymentDetails;
import pl.kumorek.ecommerce.sales.payment.PaymentGateway;
import pl.kumorek.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.kumorek.ecommerce.sales.payu.PayUPaymentGateway;
import pl.kumorek.ecommerce.sales.reservation.ReservationDetails;

import org.junit.jupiter.api.Test;
import pl.kumorek.ecommerce.sales.reservation.ReservationStorage;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class SalesTest {
    @Test
    void itShowsCurrentOffer() {
        //Arrange
        SalesFacade sales = thereIsSalesModule(new ProductCatalog(new HashMapProductStorage()));
        String customerId = thereIsCustomer("kuba");

        //Act
        Offer offer = sales.getCurrentOffer(customerId);

        //Assert
        assertEquals(BigDecimal.ZERO, offer.getTotal());
        assertEquals(0, offer.getItemsCount());
    }

    @Test
    void itAddsProductToCart() {
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.allProducts().get(0).getId();

        SalesFacade sales = thereIsSalesModule(catalog);

        String customerId = thereIsCustomer("Kuba");

        //Act
        sales.addProduct(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        //Assert
        assertEquals(BigDecimal.valueOf(10), offer.getTotal());
        assertEquals(1, offer.getItemsCount());
    }

    @Test
    void itAcceptsOffer() {
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.allProducts().get(0).getId();

        SalesFacade sales = thereIsSalesModule(catalog);

        String customerId = thereIsCustomer("Kuba");

        //Act
        sales.addProduct(customerId, productId);

        AcceptOfferRequest request = new AcceptOfferRequest();

        request
                .setEmail("john.doe@example.com")
                .setPhone("123456789")
                .setFirstName("John")
                .setLastName("Doe");

        ReservationDetails reservationDetails = sales.acceptOffer(customerId, request);

        //Assert
        assertNotNull(reservationDetails.getReservationId());
        assertNotNull(reservationDetails.getPaymentUrl());
    }

    private String thereIsCustomer(String id) {
        return id;
    }

    private SalesFacade thereIsSalesModule(ProductCatalog productCatalog) {
        return new SalesFacade(
                new CartStorage(),
                new OfferCalculator(productCatalog),
                PayUPaymentGateway.sandbox(),
                new ReservationStorage()
        );
    }

    private ProductCatalog thereIsProductCatalog() {
        ProductCatalog catalog = new ProductCatalog(new HashMapProductStorage());

        String productId = catalog.addProduct("product1", "example product");
        catalog.changePrice(productId, BigDecimal.valueOf(10));

        return catalog;
    }

    class DummyPaymentGateway implements PaymentGateway {
        @Override
        public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
            return new PaymentDetails("xyz", "http://dummy-payment-gateway/xyz");
        }
    }
}
