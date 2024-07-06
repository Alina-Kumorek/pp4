package pl.kumorek.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pl.kumorek.ecommerce.catalog.ArrayListProductStorage;
import pl.kumorek.ecommerce.catalog.ProductCatalog;

import pl.kumorek.ecommerce.sales.SalesFacade;
import pl.kumorek.ecommerce.sales.cart.CartStorage;
import pl.kumorek.ecommerce.sales.offer.OfferCalculator;
import pl.kumorek.ecommerce.sales.payu.PayUPaymentGateway;
import pl.kumorek.ecommerce.sales.reservation.ReservationStorage;

import java.math.BigDecimal;

//http://localhost:8080/

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyProductCatalog() {
        var catalog = new ProductCatalog(new ArrayListProductStorage());

        String prod1 = catalog.addProduct("Toast", "example");
        catalog.changePrice(prod1, BigDecimal.valueOf(5));
        catalog.addImage(prod1, "toast.png");

        String prod2 = catalog.addProduct("Sphere", "example");
        catalog.changePrice(prod2, BigDecimal.valueOf(10));
        catalog.addImage(prod2, "sphere.png");

        String prod3 = catalog.addProduct("Cute Cat", "example");
        catalog.changePrice(prod3, BigDecimal.valueOf(100));
        catalog.addImage(prod3, "cat.png");

        String prod4 = catalog.addProduct("Cute Dog", "example");
        catalog.changePrice(prod4, BigDecimal.valueOf(60));
        catalog.addImage(prod4, "dog.png");

        String prod5 = catalog.addProduct("Flower", "example");
        catalog.changePrice(prod5, BigDecimal.valueOf(20));
        catalog.addImage(prod5, "flower.png");

        return catalog;
    }

    @Bean
    SalesFacade createSalesFacade() {
        return new SalesFacade(
                new CartStorage(),
                new OfferCalculator(createMyProductCatalog()),
                PayUPaymentGateway.sandbox(),
                new ReservationStorage());
    }
}
