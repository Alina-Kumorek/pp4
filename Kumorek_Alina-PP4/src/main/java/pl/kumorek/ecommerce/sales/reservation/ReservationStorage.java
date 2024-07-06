package pl.kumorek.ecommerce.sales.reservation;

import pl.kumorek.ecommerce.sales.cart.Cart;
import pl.kumorek.ecommerce.sales.cart.CartLine;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ReservationStorage {
    HashMap<String, Reservation> reservations;

    public ReservationStorage() {
        this.reservations = new HashMap<>();
    }

    public Optional<Reservation> getById(String reservationId) {
        return Optional.ofNullable(reservations.get(reservationId));
    }

    public List<Reservation> getReservations() {
        return reservations.values().stream().toList();
    }

    public void save(Reservation reservation) {
        reservations.put(reservation.getReservationId(), reservation);
    }
}
