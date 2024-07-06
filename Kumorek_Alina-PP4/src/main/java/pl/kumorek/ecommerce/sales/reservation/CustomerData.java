package pl.kumorek.ecommerce.sales.reservation;

public class CustomerData {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;

    public CustomerData(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
