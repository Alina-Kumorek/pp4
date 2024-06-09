package pl.kumorek.ecommerce.sales;

import pl.kumorek.ecommerce.sales.cart.Cart;
import pl.kumorek.ecommerce.sales.cart.CartStorage;
import pl.kumorek.ecommerce.sales.offer.AcceptOfferRequest;
import pl.kumorek.ecommerce.sales.offer.Offer;
import pl.kumorek.ecommerce.sales.offer.OfferCalculator;
import pl.kumorek.ecommerce.sales.payment.PaymentDetails;
import pl.kumorek.ecommerce.sales.payment.PaymentGateway;
import pl.kumorek.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.kumorek.ecommerce.sales.reservation.Reservation;
import pl.kumorek.ecommerce.sales.reservation.ReservationDetails;
import pl.kumorek.ecommerce.sales.reservation.ReservatonRepository;

import java.util.UUID;

public class SalesFacade {
    private CartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservatonRepository reservatonRepository;

    public SalesFacade(CartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservatonRepository reservationRepository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservatonRepository = reservationRepository;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = loadCartForCustomer(customerId);

        Offer offer = offerCalculator.calculate(cart.getCartLines());

        return offer;
    }

    public void addProduct(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.add(productId);

    }

    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest request) {
        Cart cart = loadCartForCustomer(customerId);
        Offer offer = offerCalculator.calculate(cart.getCartLines());

        String reservationId = UUID.randomUUID().toString();

        PaymentDetails paymentDetails = paymentGateway.registerPayment(new RegisterPaymentRequest(
                request.getEmail(),
                request.getFirstName(),
                request.getLastName(),
                offer.getTotal(),
                reservationId));

        Reservation reservation = Reservation.of(reservationId, offer, request, paymentDetails);

        reservatonRepository.save(reservation);

        return new ReservationDetails();
    }
}
