package pl.kumorek.ecommerce.sales.cart;

import pl.kumorek.ecommerce.sales.cart.Cart;

import java.util.HashMap;
import java.util.Optional;

public class CartStorage {

    HashMap<String, Cart> carts;

    public CartStorage() {
        this.carts = new HashMap<>();
    }

    public Optional<Cart> getForCustomer(String customerId) {
        return Optional.empty();
    }
}
