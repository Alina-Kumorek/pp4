package pl.kumorek.ecommerce.sales.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    Map<String, Integer> products;

    public Cart() {
        this.products = new HashMap<>();
    }
    public static Cart empty() {
        return new Cart();
    }

    public void add(String productId) {
        if (!isInCart(productId)) {
            putIntoCart(productId);
        } else {
            increaseQuantity(productId);
        }
    }

    private void increaseQuantity(String productId) {
    }

    private void putIntoCart(String productId) {

    }

    private boolean isInCart(String productId) {
        return products.containsKey(productId);
    }

    public List<CartLine> getCartLines() {

        return products.entrySet()
                .stream()
                .map(es -> new CartLine(es.getKey(), es.getValue()))
                .toList();
    }

    public boolean isEmpty() {
        return products.values().isEmpty();
    }

    public Integer getProductCount() {
        return products.values().size();
    }
}
