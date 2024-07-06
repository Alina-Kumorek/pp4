package pl.kumorek.ecommerce.sales.payment;

import pl.kumorek.ecommerce.sales.payu.Product;

import java.math.BigDecimal;
import java.util.List;

public class RegisterPaymentRequest {
    private final String email;
    private final String phone;
    private final String firstName;
    private final String lastName;
    private final BigDecimal total;
    private final List<Product> products;
    private final String reservationId;

    public RegisterPaymentRequest(String email, String phone, String firstName, String lastName, BigDecimal total, List<Product> products, String reservationId) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.total = total;
        this.products = products;
        this.reservationId = reservationId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Integer getTotalAsGrosze() {
        return total.multiply(BigDecimal.valueOf(100)).intValueExact();
    }
}
