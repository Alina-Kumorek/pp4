package pl.kumorek.ecommerce.catalog;

import java.math.BigDecimal;
import  java.util.UUID;

public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

    public Product() {}

    public Product(UUID id, String name, String description) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public void changePrice(BigDecimal newPrice) {
        this.price = newPrice;
    }
}
