package pl.kumorek.ecommerce.sales.offer;

public class AcceptOfferRequest {
    String email;
    String phone;
    String firstName;
    String lastName;

    public String getEmail() {
        return email;
    }

    public AcceptOfferRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AcceptOfferRequest setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AcceptOfferRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AcceptOfferRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}