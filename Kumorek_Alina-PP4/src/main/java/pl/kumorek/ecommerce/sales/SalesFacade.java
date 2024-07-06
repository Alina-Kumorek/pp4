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
import pl.kumorek.ecommerce.sales.reservation.ReservationStorage;

import java.util.UUID;
import java.util.Optional;

public class SalesFacade {
    private CartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationStorage reservatonRepository;

    public SalesFacade(CartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationStorage reservationRepository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservatonRepository = reservationRepository;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = loadCartForCustomer(customerId);

        return offerCalculator.calculate(cart.getCartLines());
    }

    public void addProduct(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.add(productId);

    }


    public void removeProduct(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.remove(productId);
    }

    private Cart loadCartForCustomer(String customerId) {
        Optional<Cart> checkCart = cartStorage.getForCustomer(customerId);

        if (checkCart.isPresent()) {
            return checkCart.get();
        } else {
            Cart cart = Cart.empty();
            cartStorage.saveCart(customerId, cart);
            return cart;
        }
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest request) {

        Offer offer = getCurrentOffer(customerId);

        String reservationId = UUID.randomUUID().toString();

        PaymentDetails paymentDetails = paymentGateway.registerPayment(new RegisterPaymentRequest(
                request.getEmail(),
                request.getPhone(),
                request.getFirstName(),
                request.getLastName(),
                offer.getTotal(),
                offer.getProducts(),
                reservationId));

        Reservation reservation = Reservation.of(reservationId, offer, request, paymentDetails);

        reservatonRepository.save(reservation);

        return new ReservationDetails(reservationId, paymentDetails.getPaymentUrl());
    }

}
