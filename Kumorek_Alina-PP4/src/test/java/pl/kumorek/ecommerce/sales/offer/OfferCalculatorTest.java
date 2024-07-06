package pl.kumorek.ecommerce.sales.offer;

import pl.kumorek.ecommerce.catalog.ArrayListProductStorage;
import pl.kumorek.ecommerce.catalog.HashMapProductStorage;
import pl.kumorek.ecommerce.catalog.Product;
import pl.kumorek.ecommerce.catalog.ProductCatalog;
import pl.kumorek.ecommerce.sales.cart.Cart;
import pl.kumorek.ecommerce.sales.cart.CartLine;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class OfferCalculatorTest {

    @Test
    void itCalculatesEmptyOffer() {
        var catalog = thereIsProductCatalog();
        Cart cart = Cart.empty();
        List<CartLine> items = cart.getCartLines();
        OfferCalculator calculator = new OfferCalculator(catalog);

        Offer offer = calculator.calculate(items);

        assertEquals(BigDecimal.ZERO, offer.getTotal());
        assertEquals(0, offer.getItemsCount());
    }

    @Test
    void itCalculatesOneItemOffer() {
        var catalog = thereIsProductCatalog();
        var productList = catalog.allProducts();
        Cart cart = Cart.empty();

        cart.add(productList.get(0).getId());

        List<CartLine> items = cart.getCartLines();

        OfferCalculator calculator = new OfferCalculator(catalog);

        Offer offer = calculator.calculate(items);

        assertEquals(productList.get(0).getPrice(), offer.getTotal());
        assertEquals(1, offer.getItemsCount());
        assertFalse(offer.getOfferProducts().isEmpty());
        assertEquals(1, offer.getOfferProducts().toArray().length);
    }

    @Test
    void itCalculatesDiscounts() {
        var catalog = thereIsProductCatalog();
        var productList = catalog.allProducts();
        Cart cart = Cart.empty();
        int itemCount = 12;

        for (int i = 0; i < itemCount; i++) {
            cart.add(productList.get(0).getId());
        }

        List<CartLine> items = cart.getCartLines();

        OfferCalculator calculator = new OfferCalculator(catalog);

        Offer offer = calculator.calculate(items);

        assertEquals(testCalculator2(itemCount, productList.get(0).getPrice()), offer.getTotal());
        assertEquals(itemCount, offer.getItemsCount());
    }

    @Test
    void itCalculatesComplicatedOffer() {
        var catalog = thereIsProductCatalog();
        var productList = catalog.allProducts();
        Cart cart = Cart.empty();

        HashMap<String, Integer> itemCounts = new HashMap<>();
        itemCounts.put(productList.get(0).getId(), 4);
        itemCounts.put(productList.get(1).getId(), 7);
        itemCounts.put(productList.get(2).getId(), 18);

        int itemCountTotal = 0;

        for (String id : itemCounts.keySet()) {
            itemCountTotal += itemCounts.get(id);

            for (int i = 0; i < itemCounts.get(id); i++) {
                cart.add(id);
            }
        }

        List<CartLine> items = cart.getCartLines();

        OfferCalculator calculator = new OfferCalculator(catalog);

        Offer offer = calculator.calculate(items);

        assertEquals(testCalculator3(itemCounts, catalog), offer.getTotal());
        assertEquals(itemCountTotal, offer.getItemsCount());
    }

    private BigDecimal testCalculator(int itemCount, BigDecimal itemPrice) {

        System.out.printf("Item Count:\t%d\n", itemCount);
        System.out.printf("Item Price:\t%s PLN\n", itemPrice.toString());

        //every 5th for free
        int itemCountDiscounted = itemCount - (itemCount / 5);

        BigDecimal total = itemPrice.multiply(BigDecimal.valueOf(itemCountDiscounted));

        System.out.printf("Item Total:\t%s PLN\n", total.toString());
        System.out.println("---");

        return total;
    }

    private BigDecimal testCalculator2(int itemCount, BigDecimal itemPrice) {
        //every 5th for free
        BigDecimal total = testCalculator(itemCount, itemPrice);

        // 100 PLN -> 10 PLN discount
        BigDecimal totalDiscount = BigDecimal.valueOf(10)
                .multiply(BigDecimal.valueOf(total.intValue() / 100));

        total = total.subtract(totalDiscount);

        System.out.printf("Total:\t%s PLN\n", total.toString());
        System.out.println("---");

        return total;
    }

    private BigDecimal testCalculator3(HashMap<String, Integer> itemCounts, ProductCatalog catalog) {

        BigDecimal total = BigDecimal.ZERO;

        //sum totals for each line
        for (String id : itemCounts.keySet()) {
            total = total.add(testCalculator(itemCounts.get(id), catalog.getProductBy(id).getPrice()));
            }

        System.out.printf("Total:\t%s PLN\n", total.toString());
        System.out.println("---");

        // 100 PLN -> 10 PLN discount
        BigDecimal totalDiscount = BigDecimal.valueOf(10)
                .multiply(BigDecimal.valueOf(total.intValue() / 100));

        total = total.subtract(totalDiscount);

        System.out.printf("Total after Discount:\t%s PLN\n", total.toString());
        System.out.println("---");

        return total;
    }

    private ProductCatalog thereIsProductCatalog() {
        var catalog = new ProductCatalog(new HashMapProductStorage());

        var prod1 = catalog.addProduct("Obj1", "Nice description");
        catalog.changePrice(prod1, BigDecimal.valueOf(10));

        var prod2 = catalog.addProduct("Obj2", "Nice description");
        catalog.changePrice(prod2, BigDecimal.valueOf(20));

        var prod3 = catalog.addProduct("Obj3", "Nice description");
        catalog.changePrice(prod3, BigDecimal.valueOf(30));

        return catalog;
    }
}
