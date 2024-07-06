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

    private void putIntoCart(String productId) {
        products.put(productId, 1);
    }

    private void increaseQuantity(String productId) {
        products.put(productId, products.get(productId) + 1);
    }

    public void remove(String productId) {
        if (isInCart(productId)) {
            if (getProductQuantity(productId) > 1) {
                products.put(productId, products.get(productId) - 1);
            } else {
                products.remove(productId);
            }
        }
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

    private boolean isInCart(String productId) {
        return products.containsKey(productId);
    }

    public Integer getProductCount() {
        return products.values().size();
    }

    public Integer getProductQuantity(String productId) {
        return products.get(productId);
    }
}
