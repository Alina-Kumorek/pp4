package pl.kumorek.ecommerce.sales.cart;

import pl.kumorek.ecommerce.sales.cart.Cart;
import pl.kumorek.ecommerce.sales.cart.CartLine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class CartTest {

    @Test
    void itIsEmptyNewlyCreated() {
        Cart cart = Cart.empty();

        assertThat(cart.isEmpty())
                .isTrue();
    }

    @Test
    void isNotEmptyWhenProductAdded() {
        Cart cart = Cart.empty();
        String productId = thereIsProduct("X");

        cart.add(productId);
        assertThat(cart.isEmpty())
                .isFalse();
    }


    @Test
    void itExposeProductCountV1() {
        Cart cart = Cart.empty();
        String productId = thereIsProduct("X");

        cart.add(productId);

        assertThat(cart.getProductCount())
                .isEqualTo(1);
    }

    @Test
    void itExposeProductCountV2() {
        Cart cart = Cart.empty();
        String productX = thereIsProduct("X");
        String productY = thereIsProduct("Y");

        cart.add(productX);
        cart.add(productY);

        assertThat(cart.getProductCount())
                .isEqualTo(2);
    }

    @Test
    void itExposeProductCountV3() {
        Cart cart = Cart.empty();
        String productX = thereIsProduct("X");
        String productY = thereIsProduct("Y");

        cart.add(productX);
        cart.add(productX);
        cart.add(productY);

        assertThat(cart.getProductCount())
                .isEqualTo(2);
    }

    @Test
    void itStoresQuantityOfMultipleProducts() {
        Cart cart = Cart.empty();
        String productX = thereIsProduct("X");
        String productY = thereIsProduct("Y");

        cart.add(productX);
        cart.add(productX);
        cart.add(productX);
        cart.add(productY);
        cart.add(productY);

        assertThat(cart.getProductCount())
                .isEqualTo(2);
        assertCartContainsProductInQuantity(cart.getCartLines(), productX, 3);
        assertCartContainsProductInQuantity(cart.getCartLines(), productY, 2);
    }

    private String thereIsProduct(String productName) {
        return productName;
    }

    private void assertCartContainsProductInQuantity(List<CartLine> cartLines, String productId, Integer expectedQty) {
        assertThat(cartLines)
                .filteredOn(cartLines -> cartLines.getProductId().equals(productId))
                .extracting(CartLine::getQuantity)
                .first().isEqualTo(expectedQty);
    }
}
