package pl.kumorek.ecommerce.sales.cart;

import java.util.HashMap;
import java.util.Optional;

public class CartStorage {

    HashMap<String, Cart> carts;

    public CartStorage() {
        this.carts = new HashMap<>();
    }

    public Optional<Cart> getForCustomer(String customerId) {
        return Optional.ofNullable(carts.get(customerId));
    }

    public void saveCart(String customerId, Cart cart) {
        carts.put(customerId, cart);
    }
}
