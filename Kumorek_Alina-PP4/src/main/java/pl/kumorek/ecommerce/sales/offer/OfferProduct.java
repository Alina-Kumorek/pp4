package pl.kumorek.ecommerce.sales.offer;

public class OfferProduct {
    private String name;
    private Integer unitPrice;
    private Integer quantity;
    private String id;

    public String getName() {
        return name;
    }

    public OfferProduct setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public OfferProduct setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OfferProduct setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getId() {
        return id;
    }

    public OfferProduct setId(String id) {
        this.id = id;
        return this;
    }
}
