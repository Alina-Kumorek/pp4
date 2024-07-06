package pl.kumorek.ecommerce.sales;

import org.springframework.web.bind.annotation.*;

import pl.kumorek.ecommerce.sales.cart.Cart;
import pl.kumorek.ecommerce.sales.cart.CartLine;
import pl.kumorek.ecommerce.sales.offer.AcceptOfferRequest;
import pl.kumorek.ecommerce.sales.offer.Offer;
import pl.kumorek.ecommerce.sales.reservation.ReservationDetails;

import java.util.List;

@RestController
public class SalesController {

    SalesFacade sales;

    public SalesController(SalesFacade sales) {
        this.sales = sales;
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer() {
        String customerId = getCurrentCustomerId();
        return sales.getCurrentOffer(customerId);
    }

    @PostMapping("/api/add-product/{productId}")
    void addProduct(@PathVariable(name = "productId") String productId) {
        String customerId = getCurrentCustomerId();
        sales.addProduct(customerId, productId);
    }

    @PostMapping("/api/remove-product/{productId}")
    void removeProduct(@PathVariable(name = "productId") String productId) {
        String customerId = getCurrentCustomerId();
        sales.removeProduct(customerId, productId);
    }

    @PostMapping("/api/accept-offer")
    ReservationDetails acceptOffer(@RequestBody AcceptOfferRequest acceptOfferRequest) {
        String customerId = getCurrentCustomerId();
        return sales.acceptOffer(customerId, acceptOfferRequest);
    }

    private String getCurrentCustomerId() {
        return "example";
    }
}
