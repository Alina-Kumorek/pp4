package pl.kumorek.ecommerce;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.kumorek.ecommerce.catalog.ArrayListProductStorage;
import pl.kumorek.ecommerce.catalog.ProductCatalog;
import pl.kumorek.ecommerce.sales.SalesFacade;
import pl.kumorek.ecommerce.sales.cart.CartStorage;
import pl.kumorek.ecommerce.sales.offer.OfferCalculator;
import pl.kumorek.ecommerce.sales.payment.PayUPaymentGateway;
import pl.kumorek.ecommerce.sales.reservation.ReservatonRepository;
import test.java.pl.kumorek.ecommerce.payu.PayUTest;

//http://localhost:8080/

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("Here we go!!!");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyProductCatalog() {
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        catalog.addProduct("Lego set 8084", "niece one");
        return catalog;
    }

    @Bean
    PayUPaymentGateway createPayU () {
        return new PayUPaymentGateway(
            new PayUTest(
                
            )
        );
    }

    @Bean
    SalesFacade createSalesFacade() {
        return new SalesFacade(
            new CartStorage(),
            new OfferCalculator(),
            PayUPaymentGateway,
            new ReservatonStorage())
    }
}
