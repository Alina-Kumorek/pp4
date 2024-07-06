package pl.kumorek.ecommerce.sales.cart;

import pl.kumorek.ecommerce.sales.cart.Cart;
import pl.kumorek.ecommerce.sales.cart.CartLine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class CartTest {

    @Test
    void itIsEmptyWhenNewlyCreated() {
        Cart cart = Cart.empty();

        assertThat(cart.isEmpty())
                .isTrue();
    }

    @Test
    void isNotEmptyWhenProductWasAdded() {
        Cart cart = Cart.empty();
        String productId = thereIsProduct("X");

        cart.add(productId);
        assertThat(cart.isEmpty())
                .isFalse();
    }


    @Test
    void itExposesProductCountV1() {
        Cart cart = Cart.empty();
        String productId = thereIsProduct("X");

        cart.add(productId);

        assertThat(cart.getProductCount())
                .isEqualTo(1);
    }

    @Test
    void itExposesProductCountV2() {
        Cart cart = Cart.empty();
        String productX = thereIsProduct("X");
        String productY = thereIsProduct("Y");

        cart.add(productX);
        cart.add(productY);

        assertThat(cart.getProductCount())
                .isEqualTo(2);
    }

    @Test
    void itExposesProductCountV3() {
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
        assertCartContainsProductInQuantity(
                cart.getCartLines(), productX, 3);
        assertCartContainsProductInQuantity(
                cart.getCartLines(), productY, 2);
    }

    @Test
    void itAllowsToRemoveProducts() {
        Cart cart = Cart.empty();
        String productId = thereIsProduct("X");

        cart.add(productId);
        cart.add(productId);

        cart.remove(productId);
        cart.remove(productId);

        assertThat(cart.isEmpty())
                .isTrue();
    }

    private String thereIsProduct(String productName) {
        return productName;
    }

    private void assertCartContainsProductInQuantity(List<CartLine> cartLines, String productId, Integer expectedQty) {
        assertThat(cartLines)
                .filteredOn(cartLine -> cartLine.getProductId().equals(productId))
                .extracting(CartLine::getQuantity)
                .first()
                .isEqualTo(expectedQty);
    }
}
