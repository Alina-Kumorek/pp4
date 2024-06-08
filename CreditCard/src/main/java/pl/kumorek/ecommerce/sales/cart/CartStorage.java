package pl.kumorek.ecommerce.sales.cart;

import pl.kumorek.ecommerce.sales.cart.Cart;

import java.util.Optional;

public class CartStorage {

    public Optional<Cart> getForCustomer(String customerId) {
        return Optional.empty();
    }
}
